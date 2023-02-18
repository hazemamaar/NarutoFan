package com.example.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.base.Action
import com.example.core.base.BaseViewModel
import com.example.core.response.Resource
import com.example.home.data.Hero
import com.example.home.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class SearchAction: Action {
    data class Success(val Heroes: List<Hero>) :SearchAction()
    data class Failure(val message:String):SearchAction()
    data class Loading(val loading: Boolean):SearchAction()

    object NoHeroesFounded :SearchAction()

}
@HiltViewModel
class SearchHeroViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : BaseViewModel<SearchAction>() {
    fun searchHeroes(fetch:Boolean,query: String){
        searchUseCase.shouldFetchNow=fetch
        searchUseCase.invoke(viewModelScope,query){ res->
            when (res) {
                is Resource.Failure -> {
                    produce(SearchAction.Failure(res.message.toString()))
                }
                is Resource.Success -> {
                    if (res.data.isEmpty())
                        produce(SearchAction.NoHeroesFounded)
                    else
                    produce(SearchAction.Success(res.data))
                }
                is Resource.Progress ->{
                    produce(SearchAction.Loading(res.loading))
                }
                else -> {}
            }
        }
    }
}
