package com.example.test.data.network

import com.example.test.ui.auth.BasicAuthInterceptor
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.*

interface MyAPI {

//    @FormUrlEncoded
//    @POST("validateBic")
//    fun validationBic(
//        @Field("bic") BIC: String
//       // @Field("iban") IBAN: String
//    ) : Call<ResponseBody>

    @GET("validateBic")
    fun getValidation(@Query("bic") BIC:String ): Call<ResponseBody>



    companion object{

        val client =  OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor("106901","MmU4MDlkYWRhYzc1ZTRhMzYxMTVjNDg4ODNiNzcxNWE="))
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        operator fun invoke() : MyAPI{
            return Retrofit.Builder()
                .baseUrl("https://tyre24.alzura.com/de/de/rest/v12/utils/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(MyAPI::class.java)
        }
    }
}