package com.ssafy.sellerb.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.ActivityMainBinding
import com.ssafy.sellerb.di.component.ActivityComponent
import com.ssafy.sellerb.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>(){

    lateinit var binding: ActivityMainBinding

    companion object {
        const val TAG = "MainActivity"
    }

    lateinit var navController: NavController

    override fun provideLayoutId(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun injectDependencies(activityComponent: ActivityComponent)
    = activityComponent.inject(this)

    override fun setUpView(savedInstanceState: Bundle?) {
        navController = findNavController(R.id.container)

        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            onDestinationChanged(destination)
        }
    }
    private fun onDestinationChanged(destination: NavDestination){
        when(destination.id){
            R.id.item_home,
            R.id.item_my_page ->{
                binding.bottomNav.visibility = View.VISIBLE
            }
            else ->{
                binding.bottomNav.visibility = View.GONE
            }

        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}