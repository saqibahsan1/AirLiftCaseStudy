package com.saqib.casestudy.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

class Constants {
    object Pixabay {
        private const val PIXABAY_API_KEY = "22525948-b54c9f302e76d45065be729e8"
        private const val RESULTS_PER_PAGE = 200
        const val PIXABAY_API_PATH = ("?image_type=photo"
            + "&key=" + PIXABAY_API_KEY
            + "&per_page=" + RESULTS_PER_PAGE)
    }
}
