package com.example.mp_project.serverModel.books

import com.example.mp_project.serverModel.advertisements.AdvertisementsListItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiBooksListInterface {

    @GET("books")
    fun getBooksList(): Call<List<BooksListItem>>
}