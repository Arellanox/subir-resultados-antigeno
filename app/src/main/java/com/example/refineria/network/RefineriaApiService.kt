package com.example.refineria.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "http://bimotest.com"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RefineriaApiService{
    @GET("monitoreo_usuarios_get_all.php")
    fun getUsuarios():String
}

object RefineriaApi {
    val retrofitService : RefineriaApiService by lazy {
        retrofit.create(RefineriaApiService::class.java)
    }
}