package my.edu.tarc.jobseek.job_detail
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.jobseek.R
import my.edu.tarc.jobseek.job_list.JobViewModel

class DeleteConfirmationDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.fragment_delete_confirmation_dialog, null)
            val viewModel: JobViewModel by activityViewModels()
            var myRef = FirebaseDatabase.getInstance().getReference("Jobs")

            viewModel.selectedJob?.let { selectedJob ->

                builder.setView(view)
                    .setPositiveButton("Delete") { _, _ ->
                        myRef.child(selectedJob.company_name.toString()).child(selectedJob.job_title.toString()).removeValue()
                        Toast.makeText(requireContext(),"Record deleted successfully!",Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_jobDetailFragment_to_jobListFragment)
                    }
                    .setNegativeButton("Cancel") { _, _ ->
                        dismiss()
                    }
                builder.create()
            }


        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
