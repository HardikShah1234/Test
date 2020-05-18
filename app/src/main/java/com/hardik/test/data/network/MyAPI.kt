package com.hardik.test.data.network

import com.hardik.test.ui.auth.BasicAuthInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @GET("validateIban")
    fun getIban(@Query("iban") IBAN:String):Call<ResponseBody>

    @GET("validatePostCode")
    fun getPostCode(@Query("postCode") PostCode:String,@Query("countryCode") CountryCode:String):Call<ResponseBody>

    @GET("searchBic")
    fun getData(@Query("blz") BLZ:String,@Query("countryCode") CountryCode: String,
                @Query("location") Location:String, @Query("bankname") BankName:String,
                @Query("page") Page:String,@Query("resOnPage") ResPage:String):Call<ResponseBody>



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