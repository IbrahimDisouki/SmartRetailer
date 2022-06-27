package com.smartretailer.smartretailer.repository

import com.smartretailer.smartretailer.helpers.AuthenticationRequest
import com.smartretailer.smartretailer.helpers.SignupRespose
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpRemoteDataSource {
    @POST("v1/accounts:signUp?key=AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU")
    suspend fun signup(@Body authenticationRequest: AuthenticationRequest): SignupRespose

    companion object {

        var BASE_URL = "https://identitytoolkit.googleapis.com/"

        fun create(): SignUpRemoteDataSource {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(SignUpRemoteDataSource::class.java)
        }
    }
}