package com.nuwechallenge.mongo.data

import com.nuwechallenge.mongo.data.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component

@Component
interface MongoDBDatasource : MongoRepository<User, String> {
}