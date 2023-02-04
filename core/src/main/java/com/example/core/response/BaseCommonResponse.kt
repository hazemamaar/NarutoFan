package com.example.core.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
open class BaseCommonResponse(
    var success: Boolean? = null,
    var message: String? = null
) : Parcelable