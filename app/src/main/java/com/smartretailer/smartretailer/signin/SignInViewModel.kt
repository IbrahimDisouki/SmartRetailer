package com.smartretailer.smartretailer.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.smartretailer.smartretailer.helpers.User
import com.smartretailer.smartretailer.repository.Repository

class SignInViewModel : ViewModel() {
    var triggertransition= MutableLiveData<Boolean>()


    val repo= Repository()
    fun signin(email :String ,password:String) {
        val user= User(email, password)
        repo.signin(user)
        repo.triggersignin.observeForever {
            triggertransition.value = true
        }

    }
}