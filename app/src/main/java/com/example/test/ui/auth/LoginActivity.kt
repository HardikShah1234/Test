package com.example.test.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.test.R
import com.example.test.databinding.ActivityLoginBinding
import com.example.test.util.hide
import com.example.test.util.show
import com.example.test.util.toast
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

    override fun onFalure(message: String) {
        progress_bar.hide()
        toast(message)

    }
}
