package com.example.voiceup.domain.repo

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<String>
    suspend fun signup(email: String, password: String): Result<String>


    val currentUserId: Flow<String?>
    fun observeAuthState(): Flow<String?>
    fun logout()
}