package com.example.core.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ErrorResponse(

    val message: String? = null,

    val status: Int? = null
) : Parcelable
