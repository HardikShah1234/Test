package com.example.test.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyAPI {

    @FormUrlEncoded
    @GET("validateBic")
    fun validationBic(
        @Field("bic") BIC: String
       // @Field("iban") IBAN: String
    ) : Call<ResponseBody>

    companion object{
        operator fun invoke() : MyAPI{
            return Retrofit.Builder()
                .baseUrl("tyre24.alzura.com/de/de/rest/v12/utils")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyAPI::class.java)
        }
    }


}