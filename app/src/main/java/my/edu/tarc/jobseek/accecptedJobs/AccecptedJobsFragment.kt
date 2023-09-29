package my.edu.tarc.jobseek.accecptedJobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.jobseek.databinding.FragmentAccecptedJobsBinding
import my.edu.tarc.jobseek.headHunting.MyAdapter

class AccecptedJobsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    private var _binding: FragmentAccecptedJobsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AccecptedJobsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccecptedJobsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}