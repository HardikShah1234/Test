package com.example.test.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test.data.network.MyAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ValidIBAN {

    fun valiateIban (IBAN : String): LiveData<String>{

        val loginResponse = MutableLiveData<String>()

        val call = MyAPI().getIban(IBAN)


        call
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                    if(response.isSuccessful){
                        loginResponse.value = response.body()?.string()
                    }else{
                        loginResponse.value = response.errorBody()?.string()
                    }

                }

            })

        return loginResponse

    }
}