package com.serhatd.translate.di

import android.content.Context
import com.serhatd.translate.data.prefs.SharedPrefs
import com.serhatd.translate.data.repository.TranslateRepository
import com.serhatd.translate.data.retrofit.TranslateService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {
    @Provides
    @ViewModelScoped
    fun provideTranslateRepository(translateService: TranslateService): TranslateRepository {
        return TranslateRepository(translateService)
    }

    @Provides
    @ViewModelScoped
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPrefs {
        return SharedPrefs(context)
    }
}