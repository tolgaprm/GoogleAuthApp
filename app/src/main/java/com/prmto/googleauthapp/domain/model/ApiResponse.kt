package com.prmto.googleauthapp.domain.model

import kotlinx.serialization.Transient

@kotlinx.serialization.Serializable
data class ApiResponse(
    val success: Boolean,
    val user: User? = null,
    val message: String? = null,
    @Transient
    val error: Exception? = null
)
