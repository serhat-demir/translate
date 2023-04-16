package com.serhatd.translate.data.model

import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("language")
    val language: String,

    @SerializedName("name")
    val name: String
)