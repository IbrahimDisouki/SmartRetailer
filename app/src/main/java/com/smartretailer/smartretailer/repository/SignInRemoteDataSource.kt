package com.smartretailer.smartretailer.repository

import com.smartretailer.smartretailer.helpers.AuthenticationRequest
import com.smartretailer.smartretailer.helpers.SigninRespose
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInRemoteDataSource {
    @POST("v1/accounts:signInWithPassword?key=AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU")
    suspend fun signin(@Body authenticationRequest: AuthenticationRequest): SigninRespose


    }
