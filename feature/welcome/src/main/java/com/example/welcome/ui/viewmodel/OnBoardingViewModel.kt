package com.example.welcome.ui.viewmodel

import com.example.core.base.Action
import com.example.core.base.BaseViewModel
import com.example.welcome.data.OnBoardingData
import com.example.welcome.data.model.OnBoardingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class OnBoardingAction :Action{
 data class OnBoarding(val list: List<OnBoardingModel>) : OnBoardingAction()
}

@HiltViewModel
class OnBoardingViewModel @Inject constructor(val onBoardingData: OnBoardingData) : BaseViewModel<OnBoardingAction>() {


    fun getOnBoarding(){
        produce(OnBoardingAction.OnBoarding(onBoardingData.welcomeData()))
    }
}