package com.example.mp_project.serverModel.requestJson

data class RequestAddAdvertisement(
    val bookId: String,
    val price: Int,
    val city: String,
    val description: String,
    val phoneNumber: String,
    val imageUrl: String,
    val title: String
)