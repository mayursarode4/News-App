package com.mayursarode.newsapp.data.repository

import com.mayursarode.newsapp.data.model.Country
import com.mayursarode.newsapp.utils.COUNTRIES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryRepository @Inject constructor() {

    fun getCountries(): Flow<List<Country>> {
        return flow {
            emit(COUNTRIES)
        }
    }

}