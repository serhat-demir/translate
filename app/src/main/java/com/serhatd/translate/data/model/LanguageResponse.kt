package com.serhatd.translate.data.model

import com.google.gson.annotations.SerializedName

data class LanguageResponse(
    @SerializedName("languages")
    val languages: List<Language>
)