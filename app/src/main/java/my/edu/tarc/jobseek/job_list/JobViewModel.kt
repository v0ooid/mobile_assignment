package my.edu.tarc.jobseek.job_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JobViewModel : ViewModel() {
    var selectedIndex: Int = -1
    var selectedJob:Job?=null
    private val _jobList = MutableLiveData<List<Job>>()
    val jobList:LiveData<List<Job>> = _jobList
}