package com.example.welcome.ui.fragment


import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment

import com.example.core.extentions.navigateSafe
import com.example.core.extentions.observe
import com.example.welcome.R
import com.example.welcome.databinding.FragmentSplashBinding
import com.example.welcome.ui.viewmodel.SplashState
import com.example.welcome.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding,SplashViewModel>() {
    override fun onFragmentReady() {
        val slideAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_splashscreen)
        binding.imageSplash.animation = slideAnimation
        mViewModel.splashFinished()
        subscribeToObservers()
    }

    override val mViewModel: SplashViewModel by viewModels()
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: SplashState) {
        when (action) {
            SplashState.TimeDone -> {
                navigateSafe(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment(), container = R.id.frag_host)
            }
        }
    }
}