package com.example.mp_project.serverModel.imageUpload

import com.example.mp_project.serverModel.books.BooksListItem
import com.example.mp_project.serverModel.requestJson.RequestJsonId
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiImageUpload {

    @Multipart
    @POST("imageUpload")
    fun uploadImage(@Part file:MultipartBody.Part): Call<ImageUploadResponse>

    @POST
    fun sendAd(@Body requestJsonId: RequestJsonId): Call<ImageUploadResponse>
}