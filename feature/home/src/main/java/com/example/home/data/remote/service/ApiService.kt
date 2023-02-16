package com.example.home.data.remote.service

import com.example.core.response.BaseResponse
import com.example.home.data.Hero
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val HEROES="heroes"
        const val SEARCH="naruto/heroes/search?"
    }
    @GET(HEROES)
    suspend fun getHeroes(): BaseResponse<List<Hero>>
    @GET(SEARCH)
    suspend fun searchHero(@Query("name") query: String):BaseResponse<List<Hero>>
}