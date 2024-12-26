package com.example.yellow.auth.data.remote

import com.example.yellow.auth.domain.model.User
import kotlinx.coroutines.delay

object FakeAuthRemoteDataSource {

    // Use static data only
    private val validUsername = ""
    private val validPassword = ""

    // A simple simulation of an API call with a delay
    suspend fun login(username: String, password: String): Result<User> {
        // Simulate network delay
        delay(1500)

        // Fake validation
        return if (username == validUsername && password == validPassword) {
            Result.success(User(id = "1", username = username))
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }
}
