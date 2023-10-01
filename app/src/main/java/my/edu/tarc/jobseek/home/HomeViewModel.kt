package my.edu.tarc.jobseek.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var selectedIndex: Int = -1
    var selectedJob: JobModel? = null
}