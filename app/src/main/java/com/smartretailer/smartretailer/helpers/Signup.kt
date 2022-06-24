package com.smartretailer.smartretailer.helpers

data class Signup(
    val idToken: String,
    val email: String,
    val refreshToken: String,
    val expiresIn: String,
    val localId: String,
)
