package com.example.home.data.repo

import android.util.Log
import com.example.core.response.BaseResponse
import com.example.core.response.ErrorResponse
import com.example.core.response.NetworkResponse
import com.example.core.response.Resource
import com.example.home.data.remote.APIResponse
import com.example.home.data.remote.Hero
import com.example.home.data.remote.service.ApiService
import javax.inject.Inject

class DataRepo @Inject constructor(val apiService: ApiService) {
    suspend fun getAllNaruto() :Resource<BaseResponse<List<Hero>>> {
        Log.e("repo", "getAllNaruto: "+apiService.getHeroes().toString() )
        return try {
            val response = apiService.getHeroes()
            Resource.success(response)
        } catch (e: Exception) {
            Resource.failure(e.message)
        }
    }
}