package com.example.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.home.data.Hero

@Database(entities = [Hero::class], version = 1, exportSchema = false)
abstract class HeroDB : RoomDatabase(){
    abstract val heroDao : HeroDao
}