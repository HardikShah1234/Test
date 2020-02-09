package com.example.test.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.test.data.repositories.UserRepository
import com.example.test.data.repositories.ValidIBAN

class AuthenticationViewModel: ViewModel() {

    var BIC: String? = null
    var IBAN: String? = null

    var authListner:AuthListner? = null

    fun validateButtonClick(view:View){
        authListner?.onStarted()
        if(BIC.isNullOrEmpty() && IBAN.isNullOrEmpty()){
            authListner?.onFailure("Invalid BIC or IBAN")

        } else if (BIC!!.isNotEmpty() && IBAN.isNullOrEmpty()){
            val loginResponse = UserRepository().validationBic(BIC!!)
            authListner?.onSccess(loginResponse)
        } else if (IBAN!!.isNotEmpty() && BIC.isNullOrEmpty()){

            val loginResponse = ValidIBAN().valiateIban(IBAN!!)
            authListner?.onSccess(loginResponse)
        }
        return



    }
}