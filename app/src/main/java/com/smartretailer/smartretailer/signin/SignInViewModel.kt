package com.smartretailer.smartretailer.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.smartretailer.smartretailer.helpers.User
import com.smartretailer.smartretailer.repository.Repository

class SignInViewModel : ViewModel() {
    var triggertransition= MutableLiveData<Boolean>()
    var wrongsignininfo = MutableLiveData<Boolean>()


    val repo= Repository()
    fun signin(email :String ,password:String) {
        val user= User(email, password)
        repo.signin(user)
        repo.triggersignin.observeForever {
            triggertransition.value = true
        }
        repo.triggersigninerror.observeForever{
            wrongsignininfo.value=true
        }

    }

    fun refreshtoken(refreshtoken: String?) {
        repo.refreshtoken(refreshtoken)

    }
}