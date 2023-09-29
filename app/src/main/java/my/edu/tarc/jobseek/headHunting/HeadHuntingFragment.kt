package my.edu.tarc.jobseek.headHunting

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.edu.tarc.jobseek.R
import my.edu.tarc.jobseek.databinding.FragmentHeadHuntingBinding

class HeadHuntingFragment : Fragment(), MyClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private val viewModel: HeadHuntingViewModel by activityViewModels()

    private var _binding: FragmentHeadHuntingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeadHuntingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val searchView = view.findViewById<androidx.appcompat.widget.SearchView>(R.id.searchViewCandidate)
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return true
//            }
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // Handle search query text changes here
//                filterRecyclerView(newText)
//                return true
//            }
//        })

        binding.candidateRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding?.candidateRecyclerView?.setHasFixedSize(true)
        adapter = MyAdapter(requireContext(), this)
        recyclerView = binding.candidateRecyclerView
        recyclerView.adapter = adapter


        viewModel.allCandi.observe(viewLifecycleOwner, Observer {
            adapter.updateCandiList(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
//        (activity as AppCompatActivity).supportActionBar?.show()
        _binding = null
        viewModel.selectedIndex = -1
    }

     override fun onRecordClickListener(index: Int) {
        viewModel.selectedIndex = index
        findNavController().navigate(R.id.action_navigationE_headHunting_to_candidateDetailFragment)
    }

//    private fun filterRecyclerView(query: String?) {
//        val filteredList = mutableListOf<Candidate>()
//
//        // If the query is empty, show all jobs
//        if (query.isNullOrBlank()) {
//            filteredList.addAll(candidateList)
//        } else {
//            // Filter jobs based on the query
//            val lowerCaseQuery = query.toLowerCase()
//            for (job in candidateList) {
//                if (job.field.toLowerCase().contains(lowerCaseQuery) ||
//                    job.location.toLowerCase().contains(lowerCaseQuery)) {
//                    filteredList.add(job)
//                }
//            }
//        }
//
//        // Update the RecyclerView with the filtered list
//        adapter.updateCandiList(filteredList)
//    }
}

