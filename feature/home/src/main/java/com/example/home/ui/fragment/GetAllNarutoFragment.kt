package com.example.home.ui.fragment

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.core.extentions.gone
import com.example.core.extentions.observe
import com.example.home.databinding.FragmentGetAllNarutoBinding
import com.example.home.ui.viewmodel.GetAllNarutoViewModel
import com.example.home.ui.viewmodel.HeroesAction
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class GetAllNarutoFragment : BaseFragment<FragmentGetAllNarutoBinding, GetAllNarutoViewModel>() {
    override fun onFragmentReady() {
        mViewModel.getAllNaruto()
    }

    override val mViewModel: GetAllNarutoViewModel by viewModels()
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: HeroesAction) {
        when (action){
            is HeroesAction.Failure -> {
//                Toast.makeText(this, action.message, Toast.LENGTH_SHORT).show()
            }
            is HeroesAction.Loading -> {
                if(action.loading){
                    binding.shimmer.gone()
                }
            }
            is HeroesAction.Success -> {

            }
        }
    }

}