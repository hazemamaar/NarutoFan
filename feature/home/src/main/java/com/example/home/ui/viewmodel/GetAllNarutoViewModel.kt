package com.example.home.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.base.Action
import com.example.core.base.BaseViewModel
import com.example.core.response.Resource
import com.example.home.data.Hero
import com.example.home.domain.GetAllNarutoUseCase
import com.example.home.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class HeroesAction: Action{
    data class Success(val Heroes: List<Hero>) :HeroesAction()
    data class Failure(val message:String):HeroesAction()
    data class Loading(val loading: Boolean):HeroesAction()

    object NoHeroes :HeroesAction()

}
@HiltViewModel
class GetAllNarutoViewModel @Inject constructor(private val getAllNarutoUseCase: GetAllNarutoUseCase,private val searchUseCase: SearchUseCase) : BaseViewModel<HeroesAction>() {

    fun getAllNaruto(){
        getAllNarutoUseCase.shouldFetchNow =true
        getAllNarutoUseCase.invoke(viewModelScope) { res ->
            when (res) {
                is Resource.Failure -> {
                    produce(HeroesAction.Failure(res.message.toString()))
                }
                is Resource.Success -> {
                    if (res.data.isEmpty())
                        produce(HeroesAction.NoHeroes)
                    else
                        produce(HeroesAction.Success(res.data))
                }
                is Resource.Progress ->{

                    produce(HeroesAction.Loading(res.loading))
                }

                else -> {}
            }
        }
    }
    fun searchHeroes(query: String){
        searchUseCase.invoke(viewModelScope,query){ res->
            when (res) {
                is Resource.Failure -> {
                    produce(HeroesAction.Failure(res.message.toString()))
                }
                is Resource.Success -> {
                    produce(HeroesAction.Success(res.data))
                }
                is Resource.Progress ->{
                    produce(HeroesAction.Loading(res.loading))
                }
                else -> {}
            }
        }
        }
    }
