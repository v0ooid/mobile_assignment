package my.edu.tarc.jobseek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import my.edu.tarc.jobseek.databinding.ActivityMainBinding
import my.edu.tarc.jobseek.ui.headHunting.headHunting_Fragement
import my.edu.tarc.jobseek.ui.home.HomeFragment
import my.edu.tarc.jobseek.ui.notification.NotifcationFragment
import my.edu.tarc.jobseek.ui.profile.ProfileFragment

//TESTING GITHUB
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val homeFrag = HomeFragment()
        val notiFrag = NotifcationFragment()
        val headHuntingFrag = headHunting_Fragement()
        val profileFrag = ProfileFragment()

        setCurrentFragment(homeFrag)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_menuItem->setCurrentFragment(homeFrag)
                R.id.notification_menuItem->setCurrentFragment(notiFrag)
                R.id.headhunting_menuItem->setCurrentFragment(headHuntingFrag)
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