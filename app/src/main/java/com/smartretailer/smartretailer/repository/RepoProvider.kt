package com.smartretailer.smartretailer.repository

fun provideSignInRepo(signInRemoteDataSource: SignInRemoteDataSource): SignInRepository {
    return SignInRepository(signInRemoteDataSource)
}

fun provideSignUpRepo(signUpRemoteDataSource: SignUpRemoteDataSource): SignUpRepository {
    return SignUpRepository(signUpRemoteDataSource)
}

