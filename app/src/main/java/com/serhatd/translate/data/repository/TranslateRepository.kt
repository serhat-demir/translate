package com.serhatd.translate.data.repository

import com.serhatd.translate.data.model.LanguageResponse
import com.serhatd.translate.data.model.TranslationRequest
import com.serhatd.translate.data.model.TranslationResponse
import com.serhatd.translate.data.retrofit.TranslateService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TranslateRepository(private val translateService: TranslateService) {
    suspend fun getLanguages(): Response<LanguageResponse> {
        return withContext(Dispatchers.IO) {
            translateService.getLanguages()
        }
    }

    suspend fun translate(translationRequest: TranslationRequest): Response<TranslationResponse> {
        return withContext(Dispatchers.IO) {
            translateService.translate(translationRequest)
        }
    }
}