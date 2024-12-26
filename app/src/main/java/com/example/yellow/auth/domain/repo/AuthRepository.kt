package com.example.yellow.auth.domain.repo
import com.example.yellow.auth.domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<User>
}