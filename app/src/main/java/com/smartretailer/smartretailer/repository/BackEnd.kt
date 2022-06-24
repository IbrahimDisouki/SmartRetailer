package com.smartretailer.smartretailer.repository

import com.smartretailer.smartretailer.helpers.Signin
import com.smartretailer.smartretailer.helpers.Signup
import com.smartretailer.smartretailer.helpers.User
import com.smartretailer.smartretailer.repository.BackEnd.Companion.webAPIkey
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class BackEnd {


    companion object {
        const val webAPIkey = "AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU"
    }
}




interface ApiInterface {

    @POST("v1/accounts:signUp?key=$webAPIkey")
    suspend fun signup(@Body user: User): Signup

    @POST("v1/accounts:signInWithPassword?key=$webAPIkey")
    suspend fun signin(@Body user: User): Signin

    companion object {

        var BASE_URL = "https://identitytoolkit.googleapis.com/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}