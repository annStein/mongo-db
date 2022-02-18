package com.nuwechallenge.mongo.repository

import com.nuwechallenge.mongo.controller.model.AppException
import com.nuwechallenge.mongo.data.UserMongoDBDatasource
import com.nuwechallenge.mongo.data.model.User
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(val userDatasource: UserMongoDBDatasource) : UserRepository {
    override fun saveUser(user: User): User {
        return userDatasource.saveUser(user)
    }

    @Throws(AppException::class)
    override fun getUser(username: String): User? {
        return userDatasource.getUser(username)
    }

    override fun deleteUser(username: String) {
        return userDatasource.deleteUser(username)
    }

    override fun checkIfUserExists(username: String): Boolean {
        return userDatasource.checkIfUserExists(username)
    }
}