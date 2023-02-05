package com.example.core.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
open class BaseCommonResponse(
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("message")
    var message: String? = null
) : Parcelable