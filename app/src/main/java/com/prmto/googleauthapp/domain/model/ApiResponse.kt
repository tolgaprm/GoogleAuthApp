package com.prmto.googleauthapp.domain.model

@kotlinx.serialization.Serializable
data class ApiResponse(
    val success: Boolean,
    val user: User? = null,
    val message: String? = null
)
