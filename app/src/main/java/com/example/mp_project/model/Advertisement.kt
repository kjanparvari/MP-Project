package com.example.mp_project.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mp_project.R
import java.io.FileDescriptor
import java.io.Serializable

class Advertisement(
    val book: Book,
    val user: User,
    val imageId: Int = R.drawable.harry_potter_ad,
    val price: Int,
    val city: String,
    val time: String, // TODO: Change Type
    val phoneNumber: String,
    val description: String,
): Serializable {
}