package com.example.welcome.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.example.core.base.BaseActivity
import com.example.welcome.databinding.ActivitySplashBinding
import com.example.welcome.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val mViewModel: SplashViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }

}
