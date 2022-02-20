package com.nuwechallenge.mongo.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MongoDBConfig {

    @Value("\${connection-string}")
    lateinit var connectionString: String

    @Bean
    fun mongoClient(): MongoClient? {
        return MongoClients.create(connectionString)
    }
}