package my.edu.tarc.jobseek.job_list


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.edu.tarc.jobseek.R


class JobListFragment : Fragment(), MyClickListener {

    private lateinit var jobRecycleView : RecyclerView
    private lateinit var adapter: JobAdapter
    private val jobList = mutableListOf<Job>()
    private val viewModel:JobViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_job_list, container, false)
        jobRecycleView = view.findViewById(R.id.recyclerViewJob)
        jobRecycleView.layoutManager = LinearLayoutManager(context)
        adapter = JobAdapter(jobList,this)
        jobRecycleView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase Realtime Database reference
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("Jobs").child("Kaion Enterprise")//wait xion

        // Add a ValueEventListener to fetch data from Firebase
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                jobList.clear()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(Job::class.java)
                    item?.let { jobList.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error reading data from Firebase: ${error.message}")
            }
        })
    }

    override fun onRecordClickListener(index: Int) {
        viewModel.selectedIndex = index
        viewModel.selectedJob = jobList.getOrNull(index)
        findNavController().navigate(R.id.action_jobListFragment_to_jobDetailFragment)
    }
}