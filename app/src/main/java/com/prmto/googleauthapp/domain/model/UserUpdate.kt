package com.prmto.googleauthapp.domain.model

@kotlinx.serialization.Serializable
data class UserUpdate(
    val firstName: String,
    val lastName: String
)
