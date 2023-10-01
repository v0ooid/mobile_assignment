import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import my.edu.tarc.jobseek.accecptedJobs.acceptJobRepository
import my.edu.tarc.jobseek.headHunting.Employee

class AccecptedJobsViewModel() : ViewModel() {

    private val repository: acceptJobRepository
    private var _pendingEmployeesData = MutableLiveData<List<Pair<String, Employee>>>()
    val pendingEmployeesData: LiveData<List<Pair<String, Employee>>> = _pendingEmployeesData


    init {
        repository = acceptJobRepository().getInstance()
        repository.loadPendingEmployees(_pendingEmployeesData)
        Log.e("ViewModel", _pendingEmployeesData.toString())
    }
}
