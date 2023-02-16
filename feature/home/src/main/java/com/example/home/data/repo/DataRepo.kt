package com.example.home.data.repo

import com.example.home.data.Hero
import com.example.home.data.local.HeroDao
import com.example.home.data.remote.service.ApiService
import javax.inject.Inject

class DataRepo @Inject constructor(private val apiService: ApiService, private val heroDao: HeroDao) {
    suspend fun getAllNaruto()= apiService.getHeroes()
    suspend fun searchHeroes(query: String)=apiService.searchHero(query)
     fun getLocalHeroes()=heroDao.getLocalHeroes()

    suspend fun insertHeroes(heroes:List<Hero>)=heroDao.insertHeroes(heroes)

    fun searchLocalByName(query:String) = heroDao.searchByName(query)
}