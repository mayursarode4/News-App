package com.mayursarode.newsapp.utils

import android.content.Context
import com.mayursarode.newsapp.R

interface ResourceProvider {
    fun getStringNoInternetAvailable(): String
}

class DefaultResourceProvider(
    private val context: Context
) : ResourceProvider {
    override fun getStringNoInternetAvailable(): String {
        return context.getString(R.string.no_internet_available)
    }

}