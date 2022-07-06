package com.smartretailer.smartretailer

import android.util.Log
import com.smartretailer.smartretailer.helpers.AuthenticationRequest
import com.smartretailer.smartretailer.helpers.SigninRespose
import com.smartretailer.smartretailer.repository.SignInRemoteDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class SignInRepositoryTest {
    private fun <T> any(type: Class<T>): T {
        Mockito.any(type)
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    @Rule
    lateinit var expectedException: ExpectedException

    @Mock
    lateinit var remoteDataSource: SignInRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        runBlocking {
            `when`(remoteDataSource.signin(any((AuthenticationRequest::class.java)))).thenAnswer {
                val user = it.arguments[0] as AuthenticationRequest

                if (user.email == "Kingam.Ahmed1997@gmail.com" && user.password == "123456789") {
                    Log.e("gg", user.email)
                    return@thenAnswer SigninRespose("", "", "", "", "", true)
                } else {
                    throw HttpException(Response.error<ResponseBody>(400,
                        ResponseBody.create(MediaType.parse("plain/text"), "WRONG CREDENTIALS")))
                }
            }
        }
    }

    @Test
    fun test_signin_withcorrectcredentials() {
        val signinRespose = runBlocking {
            remoteDataSource.signin(AuthenticationRequest("Kingam.Ahmed1997@gmail.com",
                "123456789"))
        }
        assertNotNull(signinRespose)

    }

    @Test(expected = HttpException::class)
    fun test_signin_withwroncredentials() {
        runBlocking {
            remoteDataSource.signin(AuthenticationRequest("Kingm.Ahmed1997@gmail.com", "123456789"))
        }

    }

    @org.junit.After
    fun tearDown() {

    }
}