package com.smartretailer.smartretailer.repository

import com.smartretailer.smartretailer.helpers.AuthenticationRequest
import com.smartretailer.smartretailer.helpers.SigninRespose

class SignInRepository(val signInRemoteDataSource: SignInRemoteDataSource) {
    suspend fun signin(authenticationRequest: AuthenticationRequest): Result<SigninRespose> {
        return try {
            Result.success(signInRemoteDataSource.signin(authenticationRequest))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}