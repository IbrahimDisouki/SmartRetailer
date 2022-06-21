package com.smartretailer.smartretailer.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartretailer.smartretailer.helpers.Signin
import com.smartretailer.smartretailer.helpers.User
import com.smartretailer.smartretailer.repository.Repository
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    var triggertransition = MutableLiveData<Boolean>()
    var wrongsignininfo = MutableLiveData<Boolean>()


    val repo = Repository()
    fun signin(email: String, password: String) {
        val user = User(email, password)

        viewModelScope.launch {
            repo.signin(user).fold(
                { signin: Signin ->
                    triggertransition.value = true
                }, { exception: Throwable ->
                    wrongsignininfo.value = true
                }
            )
        }

    }
}