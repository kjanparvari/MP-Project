package com.example.mp_project.model

import java.io.Serializable

class User(
    val name: String,
    val phoneNumber: String,
    val city: String,
    val JoinDate: String, // TODO: Change Type
): Serializable {
}