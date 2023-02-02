package com.example.welcome.ui.fragment


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.core.base.BaseFragment
import com.example.core.extentions.*
import com.example.welcome.R
import com.example.welcome.databinding.FragmentOnBoardingBinding
import com.example.welcome.ui.adapters.OnBoardAdapter
import com.example.welcome.ui.viewmodel.OnBoardingAction
import com.example.welcome.ui.viewmodel.OnBoardingViewModel
import com.example.welcome.ui.viewmodel.SplashState
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding,OnBoardingViewModel>() {

    override val mViewModel: OnBoardingViewModel by viewModels()
    @Inject
    lateinit var onBoardAdapter : OnBoardAdapter
    override fun onFragmentReady() {


        mViewModel.getOnBoarding()
        subscribeToObservers()
        viewPager2Scrolling()
        initIndicator()
    }

    fun viewPager2Scrolling() {
        binding.viewpagerOnboard.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.indicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.finishBtn.visible()
                } else {
                    binding.finishBtn.gone()
                }
                binding.indicator.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }
    fun initIndicator() {
        binding.indicator.setSliderColor(R.color.btn, R.color.btn)
        binding.indicator.setSliderWidth(40f)
        binding.indicator.setSliderHeight(10f)
        binding.indicator.setSlideMode(IndicatorSlideMode.WORM)
        binding.indicator.setIndicatorStyle(IndicatorStyle.ROUND_RECT)
        binding.indicator.setPageSize(3)
        binding.indicator.notifyDataChanged()
    }

    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: OnBoardingAction) {
        when (action) {
            is OnBoardingAction.OnBoarding -> {
                Timber.e("${action.list}")
                Log.e("hazem","${action.list}")
                onBoardAdapter.onBoardList =action.list
                binding.viewpagerOnboard.adapter= onBoardAdapter
            }
        }
    }

}