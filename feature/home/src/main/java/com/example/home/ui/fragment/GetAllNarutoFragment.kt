package com.example.home.ui.fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseFragment
import com.example.core.extentions.*
import com.example.home.R
import com.example.home.databinding.FragmentGetAllNarutoBinding
import com.example.home.ui.adapter.HeroesAdapter
import com.example.home.ui.viewmodel.GetAllNarutoViewModel
import com.example.home.ui.viewmodel.HeroesAction
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GetAllNarutoFragment : BaseFragment<FragmentGetAllNarutoBinding, GetAllNarutoViewModel>() {
    @Inject
    lateinit var heroesAdapter: HeroesAdapter

    override fun onFragmentReady() {


        onClickHero()
        mViewModel.getAllNaruto()
        subscribeToObservers()
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
                binding.shimmer.gone()
//                Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
                action.message.showLogMessage("failed")
            }
            is HeroesAction.Loading -> {
                if (action.loading) {
                    binding.shimmer.visible()
                    Toast.makeText(context, "shimmer gone", Toast.LENGTH_SHORT).show()
                }
            }
            is HeroesAction.Success -> {
                binding.shimmer.gone()
                Toast.makeText(context, action.Heroes.toString(), Toast.LENGTH_SHORT).show()
                binding.rvNaruto.apply {
                    heroesAdapter.heroes = action.Heroes
                    adapter = heroesAdapter
                    layoutManager = LinearLayoutManager(activity)
                }

            }
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

}