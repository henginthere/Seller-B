package com.ssafy.sellerb.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.ActivityMainBinding
import com.ssafy.sellerb.di.component.ActivityComponent
import com.ssafy.sellerb.ui.base.BaseActivity
import com.ssafy.sellerb.util.Constants.CHANNEL_ID
import com.ssafy.sellerb.util.Constants.EXTRA_KEY_CONSULTING_SEQ
import javax.inject.Inject


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

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    override fun injectDependencies(activityComponent: ActivityComponent)
    = activityComponent.inject(this)

    override fun setUpView(savedInstanceState: Bundle?) {
        navController = findNavController(R.id.container)

        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            onDestinationChanged(destination)
        }

        binding.fab.setOnClickListener {
            navController.navigate(R.id.item_home)
        }

        val consultingSeq = intent!!.getLongExtra(EXTRA_KEY_CONSULTING_SEQ,0L)
        Log.e(TAG,"CONSULTING_SEQ : " + consultingSeq)
        mainSharedViewModel.consultingSeq.postValue(consultingSeq)
    }



    private fun onDestinationChanged(destination: NavDestination){
        when(destination.id){
            R.id.item_home,
            R.id.item_my_page ->{
                binding.coordinator.visibility = View.VISIBLE
            }
            else ->{
                binding.coordinator.visibility = View.GONE
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }

    override fun onNewIntent(intent: Intent?) {
        val consultingSeq = intent!!.getLongExtra(EXTRA_KEY_CONSULTING_SEQ,0L)
        Toast.makeText(this,"LONG  " + consultingSeq, Toast.LENGTH_SHORT).show()
        Log.e(TAG,"CONSULTING_SEQ : " + consultingSeq)
        mainSharedViewModel.consultingSeq.postValue(consultingSeq)
        super.onNewIntent(intent)
    }

}