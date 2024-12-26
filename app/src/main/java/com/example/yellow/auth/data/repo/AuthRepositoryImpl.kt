package com.example.yellow.auth.data.repo

import com.example.yellow.auth.data.remote.FakeAuthRemoteDataSource
import com.example.yellow.auth.domain.model.User
import com.example.yellow.auth.domain.repo.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(username: String, password: String): Result<User> {
        return FakeAuthRemoteDataSource.login(username, password)
    }
}
