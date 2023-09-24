package my.edu.tarc.jobseek.headHunting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class HeadHuntingViewModel(application: Application) : AndroidViewModel(application) {
    var searchQuery = MutableLiveData<String>("")
    var selectedIndex: Int = -1
}
