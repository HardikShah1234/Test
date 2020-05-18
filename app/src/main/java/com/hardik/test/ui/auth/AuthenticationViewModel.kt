package com.hardik.test.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.hardik.test.data.repositories.SearchData
import com.hardik.test.data.repositories.UserRepository
import com.hardik.test.data.repositories.ValidIBAN
import com.hardik.test.data.repositories.ValidPost
import com.hardik.test.ui.home.SearchActivity
import com.hardik.test.ui.home.SearchBLZActivity

class AuthenticationViewModel : ViewModel() {

    var BIC: String? = null
    var IBAN: String? = null
    var CountryCode: String? = null
    var PostCode: String? = null

    var BLZ: String? = null
    var Location: String? = null
    var Page: String? = null
    var ResPage: String? = null
    var BankName: String? = null


    var authListner: AuthListner? = null

    fun validateButtonClick(view: View) {
        authListner?.onStarted()
        if (BIC.isNullOrEmpty() && IBAN.isNullOrEmpty()) {
            authListner?.onFailure("Invalid BIC or IBAN")
            return
        } else if (BIC!!.isNotEmpty() && IBAN.isNullOrEmpty()) {
            val loginResponse = UserRepository().validationBic(BIC!!)
            authListner?.onSccess(loginResponse)
        } else if (IBAN!!.isNotEmpty() && BIC.isNullOrEmpty()) {

            val loginResponse = ValidIBAN().valiateIban(IBAN!!)
            authListner?.onSccess(loginResponse)
        }

    }

    fun onNextActivity(view: View) {
        Intent(view.context, SearchActivity::class.java).also {
            view.context.startActivity(it)
        }

    }

    fun ShowResponse(view: View) {

        authListner?.onStarted()
        if (PostCode.isNullOrEmpty() && CountryCode.isNullOrEmpty()) {
            authListner?.onFailure("Please Enter Code")
            return
        } else if (PostCode!!.isNotEmpty() && CountryCode!!.isNotEmpty()) {
            val loginResponse = ValidPost().validatePostCode(PostCode!!, CountryCode!!)
            authListner?.onSccess(loginResponse)
        }

    }

    fun NewActivity(view: View) {
        Intent(view.context, SearchBLZActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun ShowData(view: View) {
        authListner?.onStarted()
        if (BLZ.isNullOrEmpty()) {
            authListner?.onFailure("Enter Valid BLZ")
            return
        } else {
            val loginResponse = SearchData().validateSearchData(
                BLZ!!,
                CountryCode!!,
                Location!!,
                BankName!!,
                Page!!,
                ResPage!!
            )
            authListner?.onSccess(loginResponse)
        }
    }
}