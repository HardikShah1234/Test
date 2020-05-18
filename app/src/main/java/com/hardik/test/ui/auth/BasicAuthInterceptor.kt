package com.hardik.test.ui.auth

import okhttp3.Credentials
import okhttp3.Interceptor

class BasicAuthInterceptor (user: String, password: String) : Interceptor {
    private var credentials: String = Credentials.basic(user, password)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()

        return chain.proceed(request)

    }

}