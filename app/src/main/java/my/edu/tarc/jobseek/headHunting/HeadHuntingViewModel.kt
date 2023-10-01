package my.edu.tarc.jobseek.headHunting


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HeadHuntingViewModel :ViewModel() {
    var selectedIndex: Int = -1
    private val repository : CandidateRepository
    private val _allCandi = MutableLiveData<List<Employee>>()
    val allCandi : LiveData<List<Employee>> = _allCandi

    init {
        repository = CandidateRepository().getInstance()
        repository.loadCandi(_allCandi)
    }

    fun filterCandi(query: String) {
        val filteredList = allCandi.value?.filter { item ->
            val name = item.username?.toLowerCase() ?: ""
            val field = item.field?.toLowerCase() ?: ""
            val exp = item.experience?.toString()?.toLowerCase() ?: ""
            val location = item.location?.toLowerCase() ?: ""

            name.contains(query) || field.contains(query) || exp.contains(query) || location.contains(query)
        }
        _allCandi.value = filteredList!!
    }

}