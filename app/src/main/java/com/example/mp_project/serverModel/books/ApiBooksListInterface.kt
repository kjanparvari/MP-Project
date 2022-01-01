package com.example.mp_project.serverModel.books

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
//import com.example.mp_project.serverModel.books.BooksListItem
import com.example.mp_project.serverModel.requestJson.RequestJsonId
import retrofit2.http.POST

interface ApiBooksListInterface {

    @GET("books")
    fun getBooksList(): Call<List<BooksListItem>>

    @POST("bookGet")
    fun getBookById(@Body requestJsonId: RequestJsonId): Call<BooksListItem>
}