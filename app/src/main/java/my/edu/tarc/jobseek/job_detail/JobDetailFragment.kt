package my.edu.tarc.jobseek.job_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import my.edu.tarc.jobseek.R
import my.edu.tarc.jobseek.databinding.FragmentJobDetailBinding
import my.edu.tarc.jobseek.job_list.Job
import my.edu.tarc.jobseek.job_list.JobViewModel
import java.util.zip.Inflater

class JobDetailFragment : Fragment() {

    private var _binding: FragmentJobDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JobViewModel by activityViewModels()
    private var itemSelected: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJobDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedJob?.let { selectedJob ->
            binding.textViewJobResponsibility.text = selectedJob.job_responsibility
    }

    }
}