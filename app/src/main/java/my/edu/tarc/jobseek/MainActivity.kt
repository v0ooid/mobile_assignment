package my.edu.tarc.jobseek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import my.edu.tarc.jobseek.databinding.ActivityMainBinding
import my.edu.tarc.jobseek.job_detail.JobDetailFragment
import my.edu.tarc.jobseek.job_list.JobListFragment
import my.edu.tarc.jobseek.job_posting.JobPostingFragment
import my.edu.tarc.jobseek.notification.NotifcationFragment
import my.edu.tarc.jobseek.profile.ProfileFragment

//TESTING GITHUB
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFrag = JobListFragment()
        val notiFrag = JobPostingFragment()
        val profileFrag = ProfileFragment()

        setCurrentFragment(homeFrag)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home->setCurrentFragment(homeFrag)
                R.id.navigation_notifications->setCurrentFragment(notiFrag)
                R.id.navigation_profile->setCurrentFragment(profileFrag)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container ,fragment)
            commit()
        }
    }


}