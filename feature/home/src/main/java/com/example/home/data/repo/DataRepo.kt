package com.example.home.data.repo

import com.example.home.data.remote.service.ApiService
import javax.inject.Inject

class DataRepo @Inject constructor(val apiService: ApiService) {
    suspend fun getAllNaruto()=apiService.getHeroes()
}