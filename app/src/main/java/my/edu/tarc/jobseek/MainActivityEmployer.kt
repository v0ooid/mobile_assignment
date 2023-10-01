package my.edu.tarc.jobseek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import my.edu.tarc.jobseek.databinding.ActivityMainEmployerBinding

class MainActivityEmployer : AppCompatActivity() {

    private lateinit var binding: ActivityMainEmployerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainEmployerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navViewEmployer

        val navController = findNavController(R.id.nav_host_fragment_activity_main_employer)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.jobListFragment)
                binding.floatingActionButtonAddPost.visibility = View.INVISIBLE
            else
                binding.floatingActionButtonAddPost.visibility = View.VISIBLE

//                if(destination.id == R.id.jobPostingFragment)
//                    binding.navViewEmployer.visibility = View.GONE
//                else
//                {
//                    binding.navViewEmployer.visibility = View.VISIBLE
//                }
//
        }

        navView.setupWithNavController(navController)

        binding.floatingActionButtonAddPost.setOnClickListener {
            navController.navigate(R.id.action_jobListFragment_to_jobPostingFragment)
        }

    }
}