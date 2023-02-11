package com.example.core.response

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class BaseResponse<T>(
    @SerializedName("data")
    var data: @RawValue T? = null,

) : BaseCommonResponse()