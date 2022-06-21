package com.smartretailer.smartretailer.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.smartretailer.smartretailer.helpers.Signin
import com.smartretailer.smartretailer.helpers.Signup
import com.smartretailer.smartretailer.helpers.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class BackEnd {

    val webAPIkey = "AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU"
    var signup = MutableLiveData<Signup>()
    var emailerror = MutableLiveData<Boolean>()

    fun signup(user: User) {

        val apiInterface = ApiInterface.create().signup(user)
        apiInterface.enqueue(object : Callback<Signup> {
            override fun onResponse(call: Call<Signup>, response: Response<Signup>) {
                if (response.body() != null) {
                    signup.value = response.body() as Signup
                } else
                    emailerror.value = true
            }

            override fun onFailure(call: Call<Signup>, t: Throwable) {

            }

        })
    }

}

interface ApiInterface {

    @POST("v1/accounts:signUp?key=AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU")
    fun signup(@Body user: User): Call<Signup>

    @POST("v1/accounts:signInWithPassword?key=AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU")
    suspend fun signin(@Body user: User): Signin

    companion object {

        var BASE_URL = "https://identitytoolkit.googleapis.com/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}