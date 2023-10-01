package my.edu.tarc.jobseek.appiledJob

import AppliedJobAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.edu.tarc.jobseek.R
import my.edu.tarc.jobseek.home.HomeAdapter
import my.edu.tarc.jobseek.home.JobModel

class AppliedJobFragment : Fragment() {

    private val emptyList = mutableListOf<JobModel>()
    private lateinit var jobAppliedRecycleView: RecyclerView
    private lateinit var adapter: AppliedJobAdapter
    private val appliedJobList = mutableListOf<JobModel>() // Change this to a MutableList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_applied_job, container, false)
        jobAppliedRecycleView = view.findViewById(R.id.recyclerViewAppliedJob)
        jobAppliedRecycleView.layoutManager = LinearLayoutManager(context)
        adapter = AppliedJobAdapter(emptyList)
        jobAppliedRecycleView.adapter = adapter
        Log.e("tag", appliedJobList.toString())
        Log.e("tag", adapter.toString())
        jobAppliedRecycleView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("Jobs")

//        val candidateName = "Ching"
//        val chingCandidates: MutableList<Pair<String, String>> = mutableListOf()

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (companySnapshot in dataSnapshot.children) {
                        val companyName = companySnapshot.key

                        if (companySnapshot.hasChildren()) {
                            for (jobSnapshot in companySnapshot.children) {
                                val jobTitle = jobSnapshot.key

                                if (jobSnapshot.child("candidate").child("Ching").exists()) {
                                    val status =
                                        jobSnapshot.child("candidate").child("Ching")
                                            .child("status").getValue(String::class.java)

                                    val jobModel = JobModel(
                                        job_title = jobTitle,
                                        company_name = companyName,
                                        apply_status = status ?: "Status not found"
                                    )

                                    appliedJobList.add(jobModel)
                                    //for (jobModel in appliedJobList) {
                                        Log.d("AppliedJobs", appliedJobList.toString())
                                    //}
                                    //Toast.makeText(requireContext(),"RUN",Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }

                    adapter.updateData(appliedJobList)


                } else {
                    // No data found
                    Log.d("Firebase", "No data found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Log.e("Firebase", "Error: ${error.message}")
            }
        })

    }


}