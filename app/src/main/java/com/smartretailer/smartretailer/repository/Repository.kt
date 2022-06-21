package com.smartretailer.smartretailer.repository

import androidx.lifecycle.MutableLiveData
import com.smartretailer.smartretailer.helpers.Signin
import com.smartretailer.smartretailer.helpers.Signup
import com.smartretailer.smartretailer.helpers.User
import kotlin.math.sin

class Repository() {
    var backend = BackEnd()
    var triggeremailerror = MutableLiveData<Boolean>()
    var triggersignup = MutableLiveData<Signup>()
    fun signup(user: User) {
        backend.signup(user)
        backend.signup.observeForever {
            triggersignup.value = it
        }
        backend.emailerror.observeForever {
            triggeremailerror.value = true
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

