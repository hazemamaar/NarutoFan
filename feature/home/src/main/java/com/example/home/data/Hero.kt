package com.example.home.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.home.data.local.ListStringConverter
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "hero")
@TypeConverters(ListStringConverter::class)
data class Hero(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String> ,
    val appearsIn: List<String>,
    val natureTypes: List<String> ,
) : Parcelable

