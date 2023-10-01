package my.edu.tarc.jobseek.accecptedJobs

import AccecptedJobsViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.jobseek.databinding.FragmentAccecptedJobsBinding

class AcceptedJobsFragment : Fragment() {
    private var _binding: FragmentAccecptedJobsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AcceptedJobsAdapter
    private lateinit var viewModel: AccecptedJobsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccecptedJobsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AccecptedJobsViewModel::class.java)


        binding.accecptedJobsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding?.accecptedJobsRecyclerView?.setHasFixedSize(true)
        adapter = AcceptedJobsAdapter(requireContext())
        recyclerView = binding.accecptedJobsRecyclerView
        recyclerView.adapter = adapter

        viewModel.pendingEmployeesData.observe(viewLifecycleOwner, Observer {
            adapter.setPendingEmployees(it)
            Log.e("Fragment", it.toString())
        })

    }

}

