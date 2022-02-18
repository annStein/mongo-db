package com.nuwechallenge.mongo.controller.model

import org.springframework.http.HttpStatus

data class AppException(val type: ExceptionType) : Throwable()

enum class ExceptionType(val msg: String, val statusCode: HttpStatus) {
    USER_NOT_FOUND(ResponseMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND)
}