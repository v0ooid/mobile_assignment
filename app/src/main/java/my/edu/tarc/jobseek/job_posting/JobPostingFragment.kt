package my.edu.tarc.jobseek.job_posting

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.ktx.database
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import my.edu.tarc.jobseek.R
import my.edu.tarc.jobseek.databinding.FragmentJobPostingBinding
import my.edu.tarc.jobseek.job_list.Job

class JobPostingFragment : Fragment() {

    private var _binding: FragmentJobPostingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJobPostingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        insertNewJob()

        binding.leftIcon.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_jobPostingFragment_to_jobListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun insertNewJob(){

        binding.buttonPost.setOnClickListener {
            if (binding.editTextJobTitle.text.isNullOrEmpty()) {
                binding.editTextJobTitle.setError("Please fill in this field")
                return@setOnClickListener
            }

            if(binding.spinnerJobSpecialization.selectedItemPosition == 0){
                binding.textViewErrorMessage.error = "Please select a job specialization"
                binding.textViewErrorMessage.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                binding.textViewErrorMessage.visibility = View.VISIBLE
                return@setOnClickListener
            }

            if (binding.editTextJobResponsibility.text.isNullOrEmpty()) {
                binding.editTextJobResponsibility.setError("Please fill in this field")
                return@setOnClickListener
            }

            if (binding.editTextSkills.text.isNullOrEmpty()) {
                binding.editTextSkills.setError("Please fill in this field")
                return@setOnClickListener
            }

            if (binding.editTextQualification.text.isNullOrEmpty()) {
                binding.editTextQualification.setError("Please fill in this field")
                return@setOnClickListener
            }

            if (binding.editTextYearExperience.text.isNullOrEmpty()) {
                binding.editTextYearExperience.setError("Please fill in this field")
                return@setOnClickListener
            }

            if (binding.editTextNumberSalary.text.isNullOrEmpty()) {
                binding.editTextNumberSalary.setError("Please fill in this field")
                return@setOnClickListener
            }


            val companyName = binding.editTextCompanyName.text.toString()
            val jobTitle = binding.editTextJobTitle.text.toString()
            val jobSpecification = binding.spinnerJobSpecialization.selectedItem.toString()
            val radioGroupEmploymentType = binding.radioGroupEmploymentType.checkedRadioButtonId
            val jobResponsibility = binding.editTextJobResponsibility.text.toString()
            val skills = binding.editTextSkills.text.toString()
            val qualification = binding.editTextQualification.text.toString()
            val yearOfExperience = binding.editTextYearExperience.text.toString().toInt()
            val salary = binding.editTextNumberSalary.text.toString().toFloat()
            var selectedRadioButtonText: String? = null

            if (radioGroupEmploymentType != -1) {
                val selectedRadioButtonEmploymentType: RadioButton =
                    binding.radioGroupEmploymentType.findViewById(radioGroupEmploymentType)
                selectedRadioButtonText = selectedRadioButtonEmploymentType.text.toString()
            } else {
                //error
            }

            val myRef = FirebaseDatabase.getInstance().getReference("Jobs")
            val job = Job(
                companyName,
                jobTitle,
                jobSpecification,
                selectedRadioButtonText,
                jobResponsibility,
                skills,
                qualification,
                yearOfExperience,
                salary
            )

            myRef.child(companyName).child(jobTitle).setValue(job).addOnSuccessListener {
                myRef.child(companyName).child(jobTitle).child("candidate").setValue(" ")
                binding.editTextJobTitle.text.clear()
                binding.editTextCompanyName.text.clear()
                binding.spinnerJobSpecialization.setSelection(0)
                binding.radioButtonFullTime.isSelected
                binding.editTextNumberSalary.text.clear()
                binding.editTextJobResponsibility.text.clear()
                binding.editTextNumberSalary.text.clear()
                binding.editTextQualification.text.clear()
                binding.editTextYearExperience.text.clear()
                binding.editTextSkills.text.clear()
                Toast.makeText(requireContext(), "Added into database", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_jobPostingFragment_to_jobListFragment)
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to add into database", Toast.LENGTH_LONG)
                    .show()
            }

        }

    }

}