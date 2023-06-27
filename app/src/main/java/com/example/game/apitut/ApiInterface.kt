package com.example.game.apitut


import retrofit2.http.GET

interface ApiInterface {

    @GET("get_memes")
    fun getData(): retrofit2.Call<ApiModel>


}