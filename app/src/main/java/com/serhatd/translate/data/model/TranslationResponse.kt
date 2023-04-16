package com.serhatd.translate.data.model

import com.google.gson.annotations.SerializedName

data class TranslationResponse(
    @SerializedName("data")
    val data: Data
)