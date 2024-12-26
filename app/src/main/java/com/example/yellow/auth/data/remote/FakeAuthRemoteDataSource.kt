package com.example.yellow.auth.data.remote

import com.example.yellow.auth.domain.model.User
import kotlinx.coroutines.delay

object FakeAuthRemoteDataSource {

    private val validUsername = ""
    private val validPassword = ""

    suspend fun login(username: String, password: String): Result<User> {
        delay(1500)

        return if (username.isNotEmpty() && password.isNotEmpty()) {
            Result.success(User(id = "1", username = username))
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }
}
