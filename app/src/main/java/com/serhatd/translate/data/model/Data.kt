package com.serhatd.translate.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("translations")
    val translation: Translation
)