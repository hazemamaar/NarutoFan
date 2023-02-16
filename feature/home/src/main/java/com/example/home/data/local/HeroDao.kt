package com.example.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.home.data.Hero
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {
    @Query("SELECT * FROM hero")
    fun getLocalHeroes(): List<Hero>

    @Query("SELECT * FROM hero WHERE name LIKE '%' || :searchQuery || '%' ")
    fun searchByName(searchQuery: String): Flow<List<Hero>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(listOfObj: List<Hero>)
}