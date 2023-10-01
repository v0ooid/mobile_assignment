package my.edu.tarc.jobseek.headHunting

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import my.edu.tarc.jobseek.databinding.FragmentCandidateBinding

class CandidateDetailFragment : Fragment(){
    private var _binding: FragmentCandidateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HeadHuntingViewModel by activityViewModels()
    private var candiSelected: Employee? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCandidateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.leftIcon.setOnClickListener{
//            fragmentManager.popBackStack()
            findNavController().navigateUp()
        }

        viewModel.allCandi.observe(viewLifecycleOwner, Observer { candidateList ->
            // Check if selectedIndex is valid
            if (viewModel.selectedIndex >= 0 && viewModel.selectedIndex < candidateList.size) {
                candiSelected = candidateList[viewModel.selectedIndex]
                binding.textViewName.text = candiSelected?.username
                binding.textViewField.text = candiSelected?.field
                binding.textViewExperience.text = candiSelected?.experience
                binding.textViewSchool.text = candiSelected?.school
                binding.textViewLevel.text = candiSelected?.levelSchool
                binding.textViewCGPA.text = candiSelected?.cGPA.toString()

                binding.buttonContact.setOnClickListener {
                    showContactOptions()
                }

            }
        })
    }

    private fun showContactOptions() {
        val options = arrayOf("Phone", "Email", "Live Chat")

        AlertDialog.Builder(requireContext())
            .setTitle("Contact Options")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> {
                        // Phone option selected
                        val phoneNumber = candiSelected?.phoneNum
                        if (!phoneNumber.isNullOrEmpty()) {
                            val phoneIntent = Intent(Intent.ACTION_DIAL)
                            phoneIntent.data = Uri.parse("tel:$phoneNumber")
                            startActivity(phoneIntent)
                        }
                    }

                    1 -> {

                        var subject = "Regarding Job Opportunity"
                        var body = "Dear ${candiSelected?.username},\n\nI am interested in discussing a job opportunity with you..."
                        val email = candiSelected?.email

                        val selectorIntent = Intent(Intent.ACTION_SENDTO)
                        val urlString = "mailto:" + Uri.encode(email) + "?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body)
                        selectorIntent.data = Uri.parse(urlString)

                        val emailIntent = Intent(Intent.ACTION_SEND)
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
                        emailIntent.putExtra(Intent.EXTRA_TEXT, body)
                        emailIntent.selector = selectorIntent

                        startActivity(Intent.createChooser(emailIntent, "Send email"))
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}