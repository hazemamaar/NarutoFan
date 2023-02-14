package com.example.home.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Hero(
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val appearsIn: List<String>,
    val natureTypes: List<String>,
) : Parcelable

