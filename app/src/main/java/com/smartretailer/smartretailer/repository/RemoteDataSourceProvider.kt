package com.smartretailer.smartretailer.repository

import retrofit2.Retrofit

fun provideSignInRemoteDataSourceProvider(retrofit: Retrofit): SignInRemoteDataSource {
    return retrofit.create(SignInRemoteDataSource::class.java)
}

fun provideSignUpRemoteDataSourceProvider(retrofit: Retrofit): SignUpRemoteDataSource {
    return retrofit.create(SignUpRemoteDataSource::class.java)
}

