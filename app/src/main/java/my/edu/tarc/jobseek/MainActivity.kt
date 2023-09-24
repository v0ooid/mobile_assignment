package my.edu.tarc.jobseek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import my.edu.tarc.jobseek.databinding.ActivityMainBinding
import my.edu.tarc.jobseek.headHunting.HeadHuntingFragment
import my.edu.tarc.jobseek.home.HomeFragment
import my.edu.tarc.jobseek.notification.NotifcationFragment
import java.util.ServiceConfigurationError


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private var isBottomNavigationBarVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigationE_home, R.id.navigationE_noti, R.id.navigationE_headHunting, R.id.navigationE_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_home){
                actionBar?.title = "Home"
            } else if (destination.id == R.id.navigationE_noti){
                actionBar?.title = "Notifications"
            } else if (destination.id == R.id.navigationE_headHunting){
                actionBar?.title = "Head Hunting"
            }
            else if (destination.id == R.id.navigationE_profile){
                actionBar?.title = "Profile"
            } else{
                title = getString(R.string.app_name)
            }
        }


        val backPressedCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Do you want to exit?")
                    .setPositiveButton("Exit", { _, _ -> finish() })
                    .setNegativeButton("Cancel", { _, _ -> })
                builder.create().show()
            }
        }
        onBackPressedDispatcher.addCallback(backPressedCallback)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}



