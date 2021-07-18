package com.saqib.casestudy.network

import com.saqib.casestudy.model.PixaBayResponse
import com.saqib.casestudy.util.Constants.Pixabay.PIXABAY_API_PATH
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {
    @GET(PIXABAY_API_PATH)
    fun imageSearch(@Query("q") keywords: String?): Call<PixaBayResponse?>?

    @GET("$PIXABAY_API_PATH&editors_choice=true")
    fun editorsChoice(): Call<PixaBayResponse?>?
}
