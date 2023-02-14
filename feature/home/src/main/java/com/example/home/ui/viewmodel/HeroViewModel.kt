package com.example.home.ui.viewmodel

import com.example.core.base.Action
import com.example.core.base.BaseViewModel
import com.example.home.domain.GetAllNarutoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class HeroState:Action

@HiltViewModel
class HeroViewModel @Inject constructor() : BaseViewModel<HeroesAction>(){

}