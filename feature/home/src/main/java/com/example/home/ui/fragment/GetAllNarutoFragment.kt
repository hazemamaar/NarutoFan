package com.example.home.ui.fragment

import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseFragment
import com.example.core.extentions.*
import com.example.core.helpers.ConnectionLiveData
import com.example.home.R
import com.example.home.databinding.FragmentGetAllNarutoBinding
import com.example.home.ui.adapter.HeroesAdapter
import com.example.home.ui.viewmodel.GetAllNarutoViewModel
import com.example.home.ui.viewmodel.HeroesAction
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GetAllNarutoFragment : BaseFragment<FragmentGetAllNarutoBinding, GetAllNarutoViewModel>() {
    @Inject
    lateinit var heroesAdapter: HeroesAdapter

    override fun onFragmentReady() {
        ConnectionLiveData(requireContext()).observe(this) {
            if (!it) {
               getHeroes(false)
            }else{
                getHeroes(true)
                "connected".showLogMessage()
            }
        }
        binding.inputTextLayoutSearch.editText?.setOnClickListener {
            navigateSafe(R.id.action_getAllNarutoFragment_to_searchHeroFragment, container = R.id.frag_host)
        }
        onClickHero()

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
        when (action) {
            is HeroesAction.Failure -> {
               binding.apply {
                   imgMessage.setImageResource(R.drawable.network_error)
                   txtMessage.setText(R.string.server_error)
                   imgMessage.visible()
                   txtMessage.visible()
                   shimmer.gone()
               }

            }
            is HeroesAction.Loading -> {
                if (action.loading) {
                    binding.shimmer.visible()
                }
            }
            is HeroesAction.Success -> {
                binding.rvNaruto.apply {
                    heroesAdapter.heroes = action.Heroes
                    adapter = heroesAdapter
                    layoutManager = LinearLayoutManager(activity)
                }
                binding.shimmer.gone()
            }
           is HeroesAction.NoHeroes -> {
                binding.apply {
                    imgMessage.setImageResource(R.drawable.network_error)
                    txtMessage.setText(R.string.no_internet_or_cache)
                    imgMessage.visible()
                    txtMessage.visible()
                    shimmer.gone()
                }

            }
            else -> {}
        }
    }

    private fun onClickHero() {
        heroesAdapter.setOnItemClickListener { hero ->
            navigateSafe(
                GetAllNarutoFragmentDirections.actionGetAllNarutoFragmentToHeroFragment(hero),
                container = R.id.frag_host
            )
        }
    }

    private fun getHeroes(fetch:Boolean){
        mViewModel.getAllNaruto(fetch)
        subscribeToObservers()
    }
}