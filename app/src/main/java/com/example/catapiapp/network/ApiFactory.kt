package com.example.catapiapp.network

import android.telecom.Call
import com.example.catapiapp.model.Cat
import retrofit2.http.GET

interface ApiFactory {

    //https://api.thecatapi.com/v1/images/search

   @GET("v1/images/search")
   suspend fun getData(

   ) : Cat
}