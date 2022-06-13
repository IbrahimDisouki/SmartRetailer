package com.smartretailer.smartretailer.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.smartretailer.smartretailer.helpers.Signin
import com.smartretailer.smartretailer.helpers.User
import com.smartretailer.smartretailer.helpers.Signup

class Repository() {
    var backend=BackEnd()
    var triggeremailerror=MutableLiveData<Boolean>()
    var triggersigninerror=MutableLiveData<Boolean>()
    var triggersignup=MutableLiveData<Signup>()
    var triggersignin=MutableLiveData<Signin>()
    fun signup(user: User) {
        backend.signup(user)
        backend.signup.observeForever {
            triggersignup.value = it
        }
        backend.emailerror.observeForever{
            triggeremailerror.value=true
        }
    }
    fun signin(user: User) {
        backend.signin(user)
        backend.signin.observeForever {
            triggersignin.value = it
        }
        backend.signinerror.observeForever{
            triggersigninerror.value=true
        }
    }
    }

