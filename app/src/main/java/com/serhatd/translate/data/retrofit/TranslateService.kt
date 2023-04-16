package com.serhatd.translate.data.retrofit

import com.serhatd.translate.data.model.LanguageResponse
import com.serhatd.translate.data.model.TranslationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TranslateService {
    @GET("/v2/languages")
    suspend fun getLanguages(): Response<LanguageResponse>

    @POST("/v2")
    @Headers("Content-Type: application/json")
    suspend fun translate(): Response<TranslationResponse>
}