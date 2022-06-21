package com.smartretailer.smartretailer.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.smartretailer.smartretailer.helpers.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class BackEnd() {
    val webAPIkey="AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU"
     var signup = MutableLiveData<Signup>()
    var signinerror=MutableLiveData<Boolean>()
    var triggercount=MutableLiveData<Boolean>()
    var emailerror=MutableLiveData<Boolean>()
    var signin = MutableLiveData<Signin>()
    var refreshtoken = MutableLiveData<Refreshtoken>()

    fun signup(user: User)   {

    val apiInterface = ApiInterface.create().signup(user)
        apiInterface.enqueue(object :Callback<Signup>{
            override fun onResponse(call: Call<Signup>, response: Response<Signup>) {
                if(response.body()!=null) {
                    signup.value = response.body() as Signup
                    triggercount.value=true
                    Singedinuser.email= signup.value!!.email
                    Singedinuser.userid=signup.value!!.localId
                    Singedinuser.refreshtoken= signup.value!!.refreshToken
                    Singedinuser.idtoken= signup.value!!.idToken
                    triggercount.value=true
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
                    Singedinuser.email= signin.value!!.email
                    Singedinuser.userid=signin.value!!.localId
                    Singedinuser.refreshtoken= signin.value!!.refreshToken
                    Singedinuser.idtoken= signin.value!!.idToken
                    triggercount.value=true

                }
               else{
                   signinerror.value=true
                    Log.e("gg","trigger error")
            }}

            override fun onFailure(call: Call<Signin>, t: Throwable) {
            }

        })
    }

    fun refreshtoken(refreshment: String) {
        val apiInterface=ApiInterface.createrefreshtoken().refreshtoken("refresh_token",refreshment)
        apiInterface.enqueue(object :Callback<Refreshtoken>{
            override fun onResponse(call: Call<Refreshtoken>, response: Response<Refreshtoken>) {
               if(response.body()!=null){

                   refreshtoken.value=response.body() as Refreshtoken
                   Singedinuser.refreshtoken= refreshtoken.value!!.refresh_token
                   Singedinuser.idtoken= refreshtoken.value!!.id_token
                   Singedinuser.userid= refreshtoken.value!!.user_id
                   triggercount.value=true
               }
            }

            override fun onFailure(call: Call<Refreshtoken>, t: Throwable) {

            }

        })

    }

}

interface ApiInterface {

    @POST("v1/accounts:signUp?key=AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU")
    fun signup(@Body user:User) : Call<Signup>
    @POST("v1/accounts:signInWithPassword?key=AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU")
    fun signin(@Body user:User):Call<Signin>
    @FormUrlEncoded
    @POST("v1/token?key=AIzaSyD0AKakfRkdqEdugRW52coeYc5R1vpc-jU")
    fun refreshtoken(@Field("grant_type")grant_type:String ,@Field("refresh_token")refresh_token:String):Call<Refreshtoken>

    companion object {

        var BASE_URL = "https://identitytoolkit.googleapis.com/"
        var BASE_URL_REFRESH_TOKEN = "https://securetoken.googleapis.com/"
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
        fun createrefreshtoken():ApiInterface{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL_REFRESH_TOKEN)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}