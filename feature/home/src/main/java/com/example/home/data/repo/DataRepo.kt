package com.example.home.data.repo

import android.util.Log
import com.example.core.response.BaseResponse
import com.example.core.response.Resource
import com.example.home.data.remote.Hero
import com.example.home.data.remote.service.ApiService
import javax.inject.Inject

class DataRepo @Inject constructor(val apiService: ApiService) {
    suspend fun getAllNaruto()= apiService.getHeroes()

}