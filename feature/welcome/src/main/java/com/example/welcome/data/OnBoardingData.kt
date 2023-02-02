package com.example.welcome.data

import com.example.welcome.R
import com.example.welcome.data.model.OnBoardingModel
import javax.inject.Inject


class OnBoardingData @Inject constructor()  {
    public fun welcomeData():List<OnBoardingModel>{
        return listOf(OnBoardingModel(R.string.greetings,R.drawable.greetings,R.string.first_onboard_page),
            OnBoardingModel(R.string.explore,R.drawable.explore,R.string.second_onboard_page),
            OnBoardingModel(R.string.power,R.drawable.power,R.string.third_onboard_page))
    }
}