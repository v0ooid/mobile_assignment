package my.edu.tarc.jobseek.job_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.edu.tarc.jobseek.databinding.FragmentJobDetailBinding
import my.edu.tarc.jobseek.home.HomeViewModel
import my.edu.tarc.jobseek.home.JobModel

class JobDetailFragment : Fragment() {
    private var _binding: FragmentJobDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()
    private var productSelected: JobModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJobDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val candidateNameToCheck = "Biiii"
        val database = FirebaseDatabase.getInstance()
        var reference = database.getReference("Jobs")

        viewModel.selectedJob?.let { selectedJob ->
            binding.textViewJobTitleDetails.text = selectedJob.job_title
            binding.textViewCompanyNameDetails.text = selectedJob.company_name
            binding.textViewLocationDetails.text = selectedJob.work_location
            binding.textViewSalaryDetails.text = selectedJob.salary.toString()
            binding.textViewJobSpecialDetail.text = selectedJob.job_specialization
            binding.textViewTypeDetails.text = selectedJob.employment_type
            binding.textViewJobDescDetails.text = selectedJob.job_responsibility
            binding.textViewQualification.text = selectedJob.qualification
            binding.textViewSkills.text = selectedJob.skills
            binding.textViewYear.text = selectedJob.year_of_experience.toString()

            reference = database.getReference("Jobs")
                .child(selectedJob.company_name.toString()) // Replace with the actual company name
                .child(selectedJob.job_title.toString())   // Replace with the actual job title
                .child("candidate")
        }

        reference.child(candidateNameToCheck).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) { //exist in database

                    binding.btnApply.text = "Application Status: Applied"
                    binding.btnApply.isEnabled = false
                } else { //not exist
                    // "Sze Cheng" does not exist in the database
                    binding.btnApply.setOnClickListener {
                        reference.child(candidateNameToCheck).child("status").setValue("pending")

                        binding.btnApply.text = "Application Status: Applied"
                        binding.btnApply.isEnabled = false
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
