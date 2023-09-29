package my.edu.tarc.jobseek.headHunting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.jobseek.R
import my.edu.tarc.jobseek.databinding.FragmentHeadhuntingBinding

class HeadHuntingFragment : Fragment(),  RecordClickListener{

    private var _binding: FragmentHeadhuntingBinding? = null
    private val binding get() = _binding!!
    private val headHuntingViewModel: HeadHuntingViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (activity as AppCompatActivity).supportActionBar?.hide()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeadhuntingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val candiList = Constants.getCandidateData()
        binding?.candidateRecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.candidateRecyclerView?.setHasFixedSize(true)

        val candiAdapter = MyAdapter(requireContext(), this)

        binding?.candidateRecyclerView?.adapter = candiAdapter


    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Re-enable the app bar when the fragment is destroyed
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onRecordClickListener(index: Int) {
        headHuntingViewModel.selectedIndex = index
//        findNavController().navigate(R.id.action_headHuntingFragment_to_candidateDetailFragment)
    }




}