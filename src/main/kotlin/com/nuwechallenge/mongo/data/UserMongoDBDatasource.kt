package com.nuwechallenge.mongo.data

import com.nuwechallenge.mongo.controller.model.AppException
import com.nuwechallenge.mongo.controller.model.ExceptionType
import com.nuwechallenge.mongo.data.model.User
import org.springframework.stereotype.Component

@Component
class UserMongoDBDatasource(val datasource: MongoDBDatasource) : UserDatasource {
    override fun saveUser(user: User): User {
        return datasource.save(user)
    }

    override fun deleteUser(username: String) {
        return datasource.deleteById(username)
    }

    @Throws(AppException::class)
    override fun getUser(username: String): User {
        try {
            return datasource.findById(username).get()
        } catch (e: NoSuchElementException) {
            throw AppException(ExceptionType.USER_NOT_FOUND)
        }
    }

    override fun checkIfUserExists(username: String): Boolean {
        return datasource.existsById(username)
    }


}