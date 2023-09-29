package my.edu.tarc.jobseek.headHunting


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HeadHuntingViewModel :ViewModel() {
    var selectedIndex: Int = -1

    private val repiository : CandidateRepiository

    private val _allCandi = MutableLiveData<List<Candidate>>()
    val allCandi : LiveData<List<Candidate>> = _allCandi

    init {
        repiository = CandidateRepiository().getInstance()
        repiository.loadCandi(_allCandi)
    }

}