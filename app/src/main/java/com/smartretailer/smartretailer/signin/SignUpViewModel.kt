package com.smartretailer.smartretailer.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartretailer.smartretailer.helpers.Signup
import com.smartretailer.smartretailer.helpers.User
import com.smartretailer.smartretailer.repository.Repository
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    var triggertransition= MutableLiveData<Boolean>()
    var triggererror = MutableLiveData<Boolean>()


    val repo=Repository()
    fun signup(email :String ,password:String) {
        val user = User(email, password)
        viewModelScope.launch {
            repo.signup(user).fold({ signup: Signup -> triggertransition.value = true },
                { exception: Throwable ->
                    triggererror.value = true
                })

        }

    }

    }

