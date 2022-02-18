package com.nuwechallenge.mongo.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id
    val username: String,
    val age: Int? = null,
    val city: String? = null
)