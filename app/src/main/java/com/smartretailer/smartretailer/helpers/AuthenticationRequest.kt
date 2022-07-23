package com.smartretailer.smartretailer.helpers

data class AuthenticationRequest(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true,
)
