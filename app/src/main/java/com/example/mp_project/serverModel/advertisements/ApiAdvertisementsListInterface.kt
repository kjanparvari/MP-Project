package com.example.mp_project.serverModel.advertisements

import com.example.mp_project.serverModel.books.BooksListItem
import com.example.mp_project.serverModel.imageUpload.ImageUploadResponse
import com.example.mp_project.serverModel.requestJson.RequestAddAdvertisement
import com.example.mp_project.serverModel.requestJson.RequestJsonId
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiAdvertisementsListInterface {

    @GET("advertisements")
    fun getAdvertisementList(): Call<List<AdvertisementsListItem>>

    @POST("advertisementGet")
    fun getAdvertisementById(@Body requestJsonId: RequestJsonId): Call<AdvertisementsListItem>

    @POST
    fun sendAd(@Body requestJsonId: RequestAddAdvertisement): Call<AdvertisementsListItem>
}

