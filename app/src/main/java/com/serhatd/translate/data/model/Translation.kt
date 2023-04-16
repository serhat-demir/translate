package com.serhatd.translate.data.model

import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("translatedText")
    val translatedText: String
)