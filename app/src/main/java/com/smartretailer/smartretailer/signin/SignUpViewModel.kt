package com.smartretailer.smartretailer.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartretailer.smartretailer.helpers.AuthenticationRequest
import com.smartretailer.smartretailer.helpers.SignupRespose
import com.smartretailer.smartretailer.repository.SignUpRepository
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    var triggertransition= MutableLiveData<Boolean>()
    var triggererror = MutableLiveData<Boolean>()


    val repo = SignUpRepository()
    fun signup(email :String ,password:String) {
        val authenticationRequest = AuthenticationRequest(email, password)
        viewModelScope.launch {
            repo.signup(authenticationRequest)
                .fold({ signupRespose: SignupRespose -> triggertransition.value = true },
                    { exception: Throwable ->
                        triggererror.value = true
                    })

        }

    }

    }

