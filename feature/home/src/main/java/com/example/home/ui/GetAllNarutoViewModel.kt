package com.example.home.ui

import com.example.core.base.Action
import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class Narutoes: Action
@HiltViewModel
class GetAllNarutoViewModel @Inject constructor() : BaseViewModel<Narutoes>() {
}