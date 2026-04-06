package com.example.voiceup.domain.repo

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<String>
    suspend fun signup(email: String, password: String): Result<String>

    suspend fun googleLogin(idToken: String): Result<String>

    fun observeAuthState(): Flow<String?>
    fun logout()
}