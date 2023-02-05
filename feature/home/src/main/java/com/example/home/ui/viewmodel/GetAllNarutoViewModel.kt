package com.example.home.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.base.Action
import com.example.core.base.BaseViewModel
import com.example.core.response.Resource
import com.example.home.data.remote.Hero
import com.example.home.domain.GetAllNarutoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class HeroesAction: Action{
    data class Success(val Heroes: List<Hero>) :HeroesAction()
    data class Failure(val message:String):HeroesAction()
    data class Loading(val loading: Boolean):HeroesAction()
}
@HiltViewModel
class GetAllNarutoViewModel @Inject constructor(private val getAllNarutoUseCase: GetAllNarutoUseCase) : BaseViewModel<HeroesAction>() {

    fun getAllNaruto(){
        getAllNarutoUseCase.invoke(viewModelScope) { res ->
            when (res) {
                is Resource.Failure -> {
                    Log.e("success",res.message.toString())
                    produce(HeroesAction.Failure(res.message.toString()))

                }
                is Resource.Success -> {
                    Log.e("success",res.data.toString())
                    produce(HeroesAction.Success(res.data))
                }
                is Resource.Progress ->{
                    Log.e("success",res.loading.toString())
                    produce(HeroesAction.Loading(res.loading))
                }
                else -> {}
            }
        }
    }
}