package com.example.mp_project.serverModel.advertisements

import retrofit2.Call
import retrofit2.http.GET

interface ApiAdvertisementsListInterface {

    @GET("advertisements")
    fun getAdvertisementList(): Call<List<AdvertisementsListItem>>
}