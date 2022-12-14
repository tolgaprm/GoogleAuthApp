package com.prmto.googleauthapp.domain.model

@kotlinx.serialization.Serializable
data class User(
    val id: String,
    val name: String,
    val emailAddress: String,
    val profilePhoto: String
)
