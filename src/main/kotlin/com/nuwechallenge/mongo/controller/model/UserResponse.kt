package com.nuwechallenge.mongo.controller.model

import com.nuwechallenge.mongo.data.model.User
import org.springframework.http.HttpStatus

data class UserResponse(val user: User, val status: HttpStatus = HttpStatus.OK) : Response(status)