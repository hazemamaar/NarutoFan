package com.example.welcome.ui.activities

import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.viewModels
import com.example.core.base.BaseActivity
import com.example.welcome.databinding.ActivitySplashBinding
import com.example.welcome.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.net.NetworkInterface


@AndroidEntryPoint
class WelcomeActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val mViewModel: SplashViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }

}
