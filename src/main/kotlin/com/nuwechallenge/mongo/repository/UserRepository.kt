package com.nuwechallenge.mongo.repository

import com.nuwechallenge.mongo.controller.model.AppException
import com.nuwechallenge.mongo.data.model.User

interface UserRepository {
    fun saveUser(user: User): User

    @Throws(AppException::class)
    fun getUser(username: String): User?

    fun deleteUser(username: String)

    fun checkIfUserExists(username: String): Boolean
}