package com.smartretailer.smartretailer.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartretailer.smartretailer.helpers.AuthenticationRequest
import com.smartretailer.smartretailer.helpers.SigninRespose
import com.smartretailer.smartretailer.repository.SignInRepository
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    var triggertransition = MutableLiveData<Boolean>()
    var wrongsignininfo = MutableLiveData<Boolean>()


    val repo = SignInRepository()
    fun signin(email: String, password: String) {
        val authenticationRequest = AuthenticationRequest(email, password)

        viewModelScope.launch {
            repo.signin(authenticationRequest).fold(
                { signinRespose: SigninRespose ->
                    triggertransition.value = true
                }, { exception: Throwable ->
                    wrongsignininfo.value = true
                }
            )
        }

    }
}