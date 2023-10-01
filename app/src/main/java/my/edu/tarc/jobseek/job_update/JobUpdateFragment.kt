package my.edu.tarc.jobseek.job_update

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.edu.tarc.jobseek.R
import my.edu.tarc.jobseek.databinding.FragmentJobDetailBinding
import my.edu.tarc.jobseek.databinding.FragmentJobPostingBinding
import my.edu.tarc.jobseek.databinding.FragmentJobUpdateBinding
import my.edu.tarc.jobseek.job_list.Job
import my.edu.tarc.jobseek.job_list.JobViewModel

class JobUpdateFragment : Fragment() {

    private var _binding: FragmentJobUpdateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JobViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJobUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        viewModel.selectedJob?.let { selectedJob ->
            binding.editTextJobTitle.text = Editable.Factory.getInstance().newEditable(selectedJob.job_title)
            binding.editTextJobResponsibility.text = Editable.Factory.getInstance().newEditable(selectedJob.job_responsibility)
            binding.editTextSkill.text = Editable.Factory.getInstance().newEditable(selectedJob.skills)
            binding.editTextJobTitle.text = Editable.Factory.getInstance().newEditable(selectedJob.job_title)
            binding.editTextQualification.text = Editable.Factory.getInstance().newEditable(selectedJob.qualification)
            binding.editTextYearExperience.text = Editable.Factory.getInstance().newEditable(selectedJob.year_of_experience.toString())

            when (selectedJob.employment_type) {
                "Full Time" -> {
                    binding.radioButtonFullTime.isChecked = true
                }
                "Part Time" -> {
                    binding.radioButtonPartTime.isChecked = true
                }
                else -> {
                    binding.radioButtonInternship.isChecked = true
                }
            }

            var spinnerText:String = selectedJob.job_specialization.toString()
            var spinner = binding.spinnerJobSpecialization
            val spinnerAdapter = spinner.adapter

            for(position in 0 until spinnerAdapter.count){
                if(spinnerAdapter.getItem(position).toString() == spinnerText){
                    spinner.setSelection(position)
                    break
                }
            }

            binding.editTextNumberSalary.text = Editable.Factory.getInstance().newEditable(selectedJob.salary.toString())
        }


        binding.buttonSave.setOnClickListener {
            updateJobDetails()
            navController.navigate(R.id.action_jobUpdateFragment_to_jobListFragment)
        }

        binding.leftIcon.setOnClickListener{
            navController.navigateUp()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun updateJobDetails() {
        val myRef = FirebaseDatabase.getInstance().getReference("Jobs")
        var companyName = "Kaion Enterprise"

        val selectedJob = viewModel.selectedJob
        var jobId = selectedJob?.job_title // Assuming you have a jobId field in your Job data class or database

        if (jobId != null) {
            val updatesMap = HashMap<String?, Any>()

            val newJobTitle = binding.editTextJobTitle.text.toString()
            val newJobSpecialization = binding.spinnerJobSpecialization.selectedItem.toString()
            val newEmploymentTypeId = binding.radioGroupEmploymentType.checkedRadioButtonId
            var newEmploymentType: String? = null

            if (newEmploymentTypeId != -1) {
                val checkedRadioButton = binding.radioGroupEmploymentType.findViewById<RadioButton>(newEmploymentTypeId)
                newEmploymentType = checkedRadioButton.text.toString()
            }

            val newJobResponsibility = binding.editTextJobResponsibility.text.toString()
            val newSkills = binding.editTextSkill.text.toString()
            val newQualification = binding.editTextQualification.text.toString()
            val newYearOfQualification = binding.editTextYearExperience.text.toString().toIntOrNull() ?: 0
            val newSalary = binding.editTextNumberSalary.text.toString().toFloatOrNull() ?: 0f

            if (selectedJob != null) {
                if (selectedJob.job_title != newJobTitle) {
                    updatesMap["job_title"] = newJobTitle
                }

                if (selectedJob.job_specialization != newJobSpecialization) {
                    updatesMap["job_specialization"] = newJobSpecialization
                }

                if (selectedJob.employment_type != newEmploymentType) {
                    updatesMap["employment_type"] = newEmploymentType.toString()
                }

                if (selectedJob.job_responsibility != newJobResponsibility) {
                    updatesMap["job_responsibility"] = newJobResponsibility
                }

                if (selectedJob.skills != newSkills) {
                    updatesMap["skills"] = newSkills
                }

                if (selectedJob.qualification != newQualification) {
                    updatesMap["qualification"] = newQualification
                }

                if (selectedJob.year_of_experience != newYearOfQualification) {
                    updatesMap["year_of_experience"] = newYearOfQualification
                }

                if (selectedJob.salary != newSalary) {
                    updatesMap["salary"] = newSalary
                }

                if (updatesMap.isNotEmpty()) {
                    myRef.child(companyName).child(jobId).updateChildren(updatesMap)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(requireContext(), "Failed to update information", Toast.LENGTH_LONG).show()
                        }

                } else {
                    Toast.makeText(requireContext(), "No changes made", Toast.LENGTH_LONG).show()
                }

                if(jobId != newJobTitle) {
                    myRef.child(companyName)
                        .addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // Loop through all child nodes of the company
                                    for (childSnapshot in dataSnapshot.children) {
                                        val jobTitle = childSnapshot.key
                                        if (jobTitle == jobId) {
                                            // Remove the old job title and create a new one with the new title
                                            myRef.child(companyName).child(newJobTitle)
                                                .setValue(childSnapshot.value)
                                            myRef.child(companyName).child(jobTitle)
                                                .removeValue()

                                        }
                                    }
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                // Handle errors
                            }
                        })
                }

            }

        } else {
            Toast.makeText(requireContext(), "Job Title not found", Toast.LENGTH_LONG).show()
        }
    }
}