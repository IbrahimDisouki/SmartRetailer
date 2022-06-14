package com.smartretailer.smartretailer.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.smartretailer.smartretailer.helpers.Signin
import com.smartretailer.smartretailer.helpers.User
import com.smartretailer.smartretailer.helpers.Signup
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class BackEnd() {
    val webAPIkey="AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU"
     var signup = MutableLiveData<Signup>()
    var signinerror=MutableLiveData<Boolean>()
    var emailerror=MutableLiveData<Boolean>()
    var signin = MutableLiveData<Signin>()
    fun signup(user: User)   {

    val apiInterface = ApiInterface.create().signup(user)
        apiInterface.enqueue(object :Callback<Signup>{
            override fun onResponse(call: Call<Signup>, response: Response<Signup>) {
                if(response.body()!=null) {
                    signup.value = response.body() as Signup
                }
                else
                    emailerror.value=true
            }

            override fun onFailure(call: Call<Signup>, t: Throwable) {

            }

        })
     }
    fun signin(user: User){
        val apiInterface = ApiInterface.create().signin(user)
        apiInterface.enqueue(object :Callback<Signin>{
            override fun onResponse(call: Call<Signin>, response: Response<Signin>) {
                if(response.body()!=null) {
                    signin.value = response.body() as Signin

                }
               else{
                   signinerror.value=true
                    Log.e("gg","trigger error")
            }}

            override fun onFailure(call: Call<Signin>, t: Throwable) {
            }

        })
    }

}

interface ApiInterface {

    @POST("v1/accounts:signUp?key=AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU")
    fun signup(@Body user:User) : Call<Signup>
    @POST("v1/accounts:signInWithPassword?key=AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU")
    fun signin(@Body user:User):Call<Signin>

    companion object {

        var BASE_URL = "https://identitytoolkit.googleapis.com/"

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}