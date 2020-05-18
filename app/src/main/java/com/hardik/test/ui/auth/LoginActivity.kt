package com.hardik.test.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hardik.test.R
import com.hardik.test.databinding.ActivityLoginBinding
import com.hardik.test.util.hide
import com.hardik.test.util.show
import com.hardik.test.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthenticationViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListner = this


    }

    override fun onStarted() {
        progress_bar.show()

    }

    override fun onSccess(loginResponse: LiveData<String>) {
        progress_bar.hide()
        loginResponse.observe(this, Observer {
            toast(it)
        })

    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)

    }
}
