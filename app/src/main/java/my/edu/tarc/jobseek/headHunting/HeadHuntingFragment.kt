package my.edu.tarc.jobseek.headHunting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.jobseek.R
import my.edu.tarc.jobseek.databinding.FragmentHeadHuntingBinding

class HeadHuntingFragment : Fragment(), MyClickListener {
    private var _binding: FragmentHeadHuntingBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HeadHuntingAdapter
    private val viewModel: HeadHuntingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeadHuntingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.candidateRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding?.candidateRecyclerView?.setHasFixedSize(true)
        adapter = HeadHuntingAdapter(requireContext(), this)
        recyclerView = binding.candidateRecyclerView
        recyclerView.adapter = adapter

        val searchView = binding.searchBar
//       searchBar searchBar.setOnEditorActionListener{ _, actionId, _ ->
//            Log.e("tag", "This working")
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                val query = searchBar.text.toString().toLowerCase().trim()
//                Log.e("TAG", query)
//                viewModel.filterCandi(query) // Tell the ViewModel to filter the data
//                true
//            } else {
//                false
//            }
//        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val query = newText?.toLowerCase()?.trim() ?: ""
                viewModel.filterCandi(query) // Tell the ViewModel to filter the data
                return true
            }
        })

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

}

