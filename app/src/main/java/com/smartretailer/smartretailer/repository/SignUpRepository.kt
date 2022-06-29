package com.smartretailer.smartretailer.repository

import com.smartretailer.smartretailer.helpers.AuthenticationRequest
import com.smartretailer.smartretailer.helpers.SignupRespose

class SignUpRepository(private val remoteDataSource: SignUpRemoteDataSource) {
    suspend fun signup(authenticationRequest: AuthenticationRequest): Result<SignupRespose> {
        return try {
            Result.success(remoteDataSource.signup(authenticationRequest))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}