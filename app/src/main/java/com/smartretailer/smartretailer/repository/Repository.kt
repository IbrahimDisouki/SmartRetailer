package com.smartretailer.smartretailer.repository

import com.smartretailer.smartretailer.helpers.Signin
import com.smartretailer.smartretailer.helpers.Signup
import com.smartretailer.smartretailer.helpers.User

class Repository {
    var backend = BackEnd()

    suspend fun signup(user: User): Result<Signup> {
        return try {
            Result.success(ApiInterface.create().signup(user))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signin(user: User): Result<Signin> {
        return try {
            Result.success(ApiInterface.create().signin(user))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

