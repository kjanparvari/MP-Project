package com.example.mp_project.model

import java.io.Serializable

class Comment(
    val user: User,
    val time: String, //TODO: Change it's Type
    val text: String
): Serializable {
}