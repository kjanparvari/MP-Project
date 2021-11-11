package com.example.mp_project.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.io.FileDescriptor

class Announcement(
    val book: Book,
    val user: User,
    val price: Int,
    val time: String, // TODO: Change Type
    val description: String,


) {
}