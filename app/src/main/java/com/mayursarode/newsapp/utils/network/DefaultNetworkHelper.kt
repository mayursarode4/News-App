package com.mayursarode.newsapp.utils.network

import android.content.Context
import android.net.ConnectivityManager

class DefaultNetworkHelper constructor(private val context: Context) : NetworkHelper {
    override fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }
}