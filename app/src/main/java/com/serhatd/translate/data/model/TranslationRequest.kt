package com.serhatd.translate.data.model

data class TranslationRequest(
    val q: String,
    val source: String,
    val target: String
)