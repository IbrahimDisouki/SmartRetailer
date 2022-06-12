package com.smartretailer.smartretailer.signin

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.smartretailer.smartretailer.helpers.User
import com.smartretailer.smartretailer.repository.Repository

class SignUpViewModel : ViewModel() {
    var triggertransition= MutableLiveData<Boolean>()


    val repo=Repository()
    fun signup(email :String ,password:String) {
        val user=User(email, password)
       repo.signup(user)
        repo.triggersignup.observeForever {
            triggertransition.value = true
        }

    }

}