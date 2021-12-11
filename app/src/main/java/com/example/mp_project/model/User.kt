package com.example.mp_project.model

import java.io.Serializable

class User(
    val email: String,
    val password: String,
    val joinDate: String, // TODO: Change Type
): Serializable {
}