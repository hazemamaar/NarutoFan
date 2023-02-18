package com.example.welcome.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.base.Action
import com.example.core.base.BaseViewModel
import com.example.welcome.data.PreDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

sealed class SplashState : Action {
    object OnBoarding : SplashState()
    object Home : SplashState()
}

@HiltViewModel
class SplashViewModel @Inject constructor(private var preDataStore: PreDataStore) :
    BaseViewModel<SplashState>() {

    fun splashFinished() {


        viewModelScope.launch {

            preDataStore.readFromDataStore.onEach {
                withContext(Dispatchers.Main) {
                    delay(3000)
                }
                if (it)
                    produce(SplashState.Home)
                else {
                    preDataStore.saveToDataStore(true)
                    produce(SplashState.OnBoarding)
                }
            }.launchIn(
                viewModelScope
            )
        }


    }
}