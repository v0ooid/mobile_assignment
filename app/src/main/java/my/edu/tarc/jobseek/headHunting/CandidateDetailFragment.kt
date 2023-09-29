package my.edu.tarc.jobseek.headHunting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import my.edu.tarc.jobseek.databinding.FragmentCandidateBinding

class CandidateDetailFragment : Fragment(){
    private var _binding: FragmentCandidateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCandidateBinding.inflate(inflater, container, false)

       return binding.root
    }


}