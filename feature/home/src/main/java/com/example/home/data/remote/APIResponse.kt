package com.example.home.data.remote

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class APIResponse(
    @SerializedName("heroes")
    val heroes: List<Hero>
)