package com.serhatd.translate.ui.intro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhatd.translate.data.prefs.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(private val prefs: SharedPrefs): ViewModel() {
    val introSkippedObserver = MutableLiveData<Boolean>()

    fun setIntroSkipped() {
        prefs.setIntroSkipped()
        introSkippedObserver.value = true
    }
}