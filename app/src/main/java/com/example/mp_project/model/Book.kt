package com.example.mp_project.model

import androidx.annotation.DrawableRes
import java.io.Serializable

class Book(
    @DrawableRes val imageId: Int,
    val title: String,
    val author: String,
    val category: String,
    val score: Double,
    val summary: String,
    val comments: List<Comment>,
    val advertisements: List<Advertisement>
): Serializable {
}