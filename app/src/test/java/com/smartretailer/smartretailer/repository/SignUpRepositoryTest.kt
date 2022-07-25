package com.smartretailer.smartretailer.repository

import android.util.Log
import com.smartretailer.smartretailer.helpers.AuthenticationRequest
import com.smartretailer.smartretailer.helpers.SignupRespose
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

class SignUpRepositoryTest {
    private fun <T> any(type: Class<T>): T {
        Mockito.any(type)
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    @Rule
    lateinit var expectedException: ExpectedException

    @Mock
    lateinit var remoteDataSource: SignUpRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        runBlocking {
            Mockito.`when`(remoteDataSource.signup(any((AuthenticationRequest::class.java))))
                .thenAnswer {
                    val user = it.arguments[0] as AuthenticationRequest

                    if (user.email == "Kingam.Ahmed1997@gmail.com" && user.password == "123456789") {
                        Log.e("gg", user.email)
                        return@thenAnswer SignupRespose("", "", "", "", "")
                    } else {
                        throw HttpException(Response.error<ResponseBody>(400,
                            "WRONG CREDENTIALS".toResponseBody("plain/text".toMediaTypeOrNull())))
                    }
                }
        }
    }

    @Test
    fun test_signin_withcorrectcredentials() {
        val signupRespose = runBlocking {
            remoteDataSource.signup(AuthenticationRequest("Kingam.Ahmed1997@gmail.com",
                "123456789"))
        }
        assertNotNull(signupRespose)

    }

    @Test(expected = HttpException::class)
    fun test_signin_withwroncredentials() {
        runBlocking {
            remoteDataSource.signup(AuthenticationRequest("Kingm.Ahmed1997@gmail.com", "123456789"))
        }

    }

}

