package my.edu.tarc.jobseek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import my.edu.tarc.jobseek.databinding.ActivityMainBinding
import my.edu.tarc.jobseek.home.HomeFragment
import my.edu.tarc.jobseek.login.LoginFragment
import my.edu.tarc.jobseek.notification.NotifcationFragment
import my.edu.tarc.jobseek.profile.ProfileFragment
import my.edu.tarc.jobseek.ui.headHunting.headHunting_Fragement
import my.edu.tarc.jobseek.ui.home.HomeFragment
import my.edu.tarc.jobseek.ui.notification.NotifcationFragment
import my.edu.tarc.jobseek.ui.profile.ProfileFragment

//TESTING GITHUB
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFrag = HomeFragment()
        val notiFrag = NotifcationFragment()
        val headHuntingFrag = headHunting_Fragement()
        val profileFrag = ProfileFragment()
        val loginFrag = LoginFragment()
        setCurrentFragment(homeFrag)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home->setCurrentFragment(homeFrag)
                R.id.navigation_notifications->setCurrentFragment(notiFrag)
                R.id.navigation_profile->setCurrentFragment(loginFrag)
                R.id.headhunting_menuItem->setCurrentFragment(headHuntingFrag)
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