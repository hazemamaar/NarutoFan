package com.example.home.data.remote.service

import com.example.core.response.BaseResponse
import com.example.core.response.ErrorResponse
import com.example.core.response.NetworkResponse
import com.example.home.data.remote.APIResponse
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val HEROES="heroes"
    }
    @GET(HEROES)
    suspend fun getHeroes():NetworkResponse<BaseResponse<APIResponse>,ErrorResponse>
}