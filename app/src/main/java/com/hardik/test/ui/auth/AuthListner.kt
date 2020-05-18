package com.hardik.test.ui.auth

import androidx.lifecycle.LiveData

interface AuthListner {

    fun onStarted()
    fun onSccess(loginResponse: LiveData<String>)
    fun onFailure(message: String)


}