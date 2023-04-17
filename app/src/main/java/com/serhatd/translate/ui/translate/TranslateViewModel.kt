package com.serhatd.translate.ui.translate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhatd.translate.data.model.Language
import com.serhatd.translate.data.model.TranslationRequest
import com.serhatd.translate.data.prefs.SharedPrefs
import com.serhatd.translate.data.repository.TranslateRepository
import com.serhatd.translate.ui.helper.StringCode
import com.serhatd.translate.ui.helper.ToastManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(private val prefs: SharedPrefs, private val repo: TranslateRepository, private val toast: ToastManager): ViewModel() {
    val introObserver = MutableLiveData<Boolean>()
    val languages = MutableLiveData<List<Language>>()
    val translatedText = MutableLiveData<String>()

    fun isIntroSkipped(): Boolean {
        introObserver.value = prefs.isIntroSkipped()
        return prefs.isIntroSkipped()
    }

    fun getLanguages() {
        viewModelScope.launch {
            val response = repo.getLanguages()
            if (response.isSuccessful && response.body() != null) {
                languages.value = response.body()!!.languages
            } else {
                toast.showToast(StringCode.SOMETHING_WENT_WRONG)
            }
        }
    }

    fun translate(q: String, srcLangIndex: Int, tarLangIndex: Int) {
        if (q.trim().isEmpty()) {
            toast.showToast(StringCode.SRC_TEXT_EMPTY)
            return
        }

        if (languages.value == null) {
            toast.showToast(StringCode.SOMETHING_WENT_WRONG)
            return
        }

        if (srcLangIndex == -1 || tarLangIndex == -1) {
            toast.showToast(StringCode.SELECT_LANGUAGE)
            return
        }

        if (srcLangIndex == tarLangIndex) {
            toast.showToast(StringCode.SAME_LANGUAGE)
            return
        }

        val srcLang = languages.value!![srcLangIndex].language
        val tarLang = languages.value!![tarLangIndex].language

        val translationRequest = TranslationRequest(q, srcLang, tarLang)
        viewModelScope.launch {
            val response = repo.translate(translationRequest)
            if (response.isSuccessful && response.body() != null) {
                translatedText.value = response.body()!!.data.translation.translatedText
            } else {
                toast.showToast(StringCode.SOMETHING_WENT_WRONG)
            }
        }
    }
}