package com.hardik.test.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hardik.test.data.network.MyAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ValidPost {

    fun validatePostCode (PostCode : String,CountryCode: String ) : LiveData<String>{

        val loginResponse = MutableLiveData<String>()

        val call = MyAPI().getPostCode(PostCode,CountryCode)

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