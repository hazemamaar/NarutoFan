package com.example.welcome.ui.viewmodel

import com.example.core.base.Action
import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

sealed class SplashState : Action {
    object TimeDone : SplashState()
}

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel<SplashState>() {

    fun splashFinished() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                delay(3000)
            }
            produce(SplashState.TimeDone)
        }
    }
}