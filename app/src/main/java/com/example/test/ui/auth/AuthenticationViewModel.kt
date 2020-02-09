package com.example.test.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.test.data.repositories.UserRepository

class AuthenticationViewModel : ViewModel() {

    var BIC: String? = null
    //var IBAN: String? = null

    var authListner:AuthListner? = null

    fun validateButtonClick(view:View){
        authListner?.onStarted()
        if(BIC.isNullOrEmpty()){
            authListner?.onFailure("Invalid BIC")

        } else{
            val loginResponse = UserRepository().validationBic(BIC!!)
            authListner?.onSccess(loginResponse)
        }
        return



    }
}