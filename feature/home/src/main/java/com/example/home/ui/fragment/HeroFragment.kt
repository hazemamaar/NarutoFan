package com.example.home.ui.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.core.base.BaseFragment
import com.example.core.extentions.popBack
import com.example.home.R
import com.example.home.common.uitils.Constant
import com.example.home.data.Hero
import com.example.home.databinding.FragmentHeroBinding
import com.example.home.ui.adapter.RelationsAdapter
import com.example.home.ui.viewmodel.HeroViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HeroFragment : BaseFragment<FragmentHeroBinding, HeroViewModel>() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    override val mViewModel: HeroViewModel
            by viewModels()
    @Inject
    lateinit var glide: RequestManager
    @Inject
    lateinit var familyAdapter: RelationsAdapter
    @Inject
    lateinit var abilityAdapter: RelationsAdapter
    @Inject
    lateinit var typeAdapter: RelationsAdapter
    private val args: HeroFragmentArgs by navArgs()
    override fun onFragmentReady() {
        handleBottomSheet()
        fillHero()
        binding.imgCancel.setOnClickListener {
            popBack(R.id.frag_host)
        }
    }

    private fun fillHero() {
        args.hero.apply {
            handleUi(this)
            setUpFamilyRV(family)
            setUpAbilityRV(appearsIn)
            setUpTypeRV(natureTypes)
        }
    }

    private fun handleUi(hero: Hero) {
        binding.bottom.apply {
            narutoName.text = hero.name
            aboutText.text = hero.about
            powerValue.text = hero.power.toString()
            monthValue.text = hero.month
            birthdayValue.text = hero.day

        }
        glide.load("${Constant.BASE_URL}${hero.image}").into(binding.imgHero)
    }

    private fun handleBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottom.bottomSheet)
        bottomSheetBehavior.peekHeight = 450
        bottomSheetBehavior.maxHeight = 1300
        bottomSheetBehavior.isHideable = false
        //#3 Listening to State Changes of BottomSheet
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> "Close Persistent Bottom Sheet"
                    BottomSheetBehavior.STATE_COLLAPSED -> "Open Persistent Bottom Sheet"

                    else -> "Persistent Bottom Sheet"
                }
            }
        })
        binding.bottom.bottomSheet.setOnClickListener {
            val state =
                if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED)
                    BottomSheetBehavior.STATE_COLLAPSED
                else
                    BottomSheetBehavior.STATE_EXPANDED
            bottomSheetBehavior.state = state
        }
    }

    private fun setUpFamilyRV(family:List<String>) {
        binding.bottom.rvFamily.apply {
            familyAdapter.relations = family
            adapter = familyAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setUpAbilityRV(abilities:List<String>) {
        binding.bottom.rvAbilities.apply {
            abilityAdapter.relations = abilities
            adapter = abilityAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setUpTypeRV(types:List<String>) {
        binding.bottom.rvNarutoType.apply {
            typeAdapter.relations = types
            adapter = typeAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}




