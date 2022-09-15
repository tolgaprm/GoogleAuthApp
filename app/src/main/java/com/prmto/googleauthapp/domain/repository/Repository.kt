package com.prmto.googleauthapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun saveSignedInState(signedIn: Boolean)

    fun readSignedInState(): Flow<Boolean>
}