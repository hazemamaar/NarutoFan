package com.example.home.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseFragment
import com.example.core.extentions.*
import com.example.core.helpers.ConnectionLiveData
import com.example.home.R
import com.example.home.databinding.FragmentSearchHeroBinding
import com.example.home.ui.adapter.HeroesAdapter
import com.example.home.ui.fragment.GetAllNarutoFragmentDirections.Companion.actionGetAllNarutoFragmentToHeroFragment
import com.example.home.ui.viewmodel.HeroesAction
import com.example.home.ui.viewmodel.SearchAction
import com.example.home.ui.viewmodel.SearchHeroViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchHeroFragment : BaseFragment<FragmentSearchHeroBinding,SearchHeroViewModel>() {
    @Inject
    lateinit var heroesAdapter: HeroesAdapter
    var isInternet :Boolean ?=false
    override fun onFragmentReady() {
        binding.apply {
            imgMessage.setImageResource(R.drawable.search_document)
            txtMessage.setText(R.string.search_text)
            imgMessage.visible()
            txtMessage.visible()
            shimmer.gone()
        }
        ConnectionLiveData(requireContext()).observe(this) {
            isInternet =it

        }
        onClickHero()
                binding.inputTextLayoutSearch.editText?.doOnTextChanged { text, _, _, _ ->
                    binding.imgMessage.gone()
                    binding.txtMessage.gone()
            if(text!!.isEmpty()){
             heroesAdapter.heroes = emptyList()

            }else{

                if (!isInternet!!) {
                    preSearch(false,text.toString().trim())
                }else{
                    preSearch(true,text.toString().trim())
                }
            }

        }
        binding.inputTextLayoutSearch.setEndIconOnClickListener {
            popBack(R.id.frag_host)
        }

        subscribeToObservers()
    }

    override val mViewModel: SearchHeroViewModel
        by viewModels()
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: SearchAction) {
        when (action) {
            is SearchAction.Failure -> {
                binding.apply {
                    imgMessage.setImageResource(R.drawable.network_error)
                    txtMessage.setText(R.string.server_error)
                    imgMessage.visible()
                    txtMessage.visible()
                    shimmer.gone()
                }

            }
            is SearchAction.Loading -> {
                if (action.loading) {
                    binding.imgMessage.gone()
                    binding.txtMessage.gone()
                    binding.shimmer.visible()
                }
            }
            is SearchAction.Success -> {
                binding.rvNaruto.apply {
                    heroesAdapter.heroes = action.Heroes
                    adapter = heroesAdapter
                    layoutManager = LinearLayoutManager(activity)
                }
                binding.shimmer.gone()
            }
            is SearchAction.NoHeroesFounded -> {
                binding.apply {
                    imgMessage.setImageResource(R.drawable.search_document)
                    txtMessage.setText(R.string.search_text)
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
                SearchHeroFragmentDirections.actionSearchHeroFragmentToHeroFragment(hero),
                container = R.id.frag_host
            )
        }
    }

   private fun preSearch(fetch:Boolean,query:String){
        mViewModel.searchHeroes(fetch,query)
        subscribeToObservers()

    }
}