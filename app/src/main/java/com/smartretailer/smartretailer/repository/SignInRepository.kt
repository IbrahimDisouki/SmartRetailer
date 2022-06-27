package com.smartretailer.smartretailer.repository

import com.smartretailer.smartretailer.helpers.AuthenticationRequest
import com.smartretailer.smartretailer.helpers.SigninRespose

class SignInRepository {
    suspend fun signin(authenticationRequest: AuthenticationRequest): Result<SigninRespose> {
        return try {
            Result.success(SignInRemoteDataSource.create().signin(authenticationRequest))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}