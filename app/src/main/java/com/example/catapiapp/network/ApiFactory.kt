package com.example.catapiapp.network

import com.example.catapiapp.model.NewCatModel
import retrofit2.http.GET

interface ApiFactory {

    //https://api.thecatapi.com/v1/images/search

   @GET("v1/images/search")
   suspend fun getData() : NewCatModel
}