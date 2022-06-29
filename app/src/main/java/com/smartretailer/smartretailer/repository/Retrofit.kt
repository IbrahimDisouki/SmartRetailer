package com.smartretailer.smartretailer.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit {
    val BASE_URL = "https://identitytoolkit.googleapis.com/"
    val retro = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    return retro
}


