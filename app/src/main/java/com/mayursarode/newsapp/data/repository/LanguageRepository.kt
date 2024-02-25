package com.mayursarode.newsapp.data.repository

import com.mayursarode.newsapp.data.model.Language
import com.mayursarode.newsapp.utils.LANGUAGES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepository @Inject constructor() {

    fun getLanguages(): Flow<List<Language>> {
        return flow {
            emit(LANGUAGES)
        }
    }

}