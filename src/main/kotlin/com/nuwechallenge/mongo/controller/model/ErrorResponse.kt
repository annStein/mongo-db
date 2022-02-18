package com.nuwechallenge.mongo.controller.model

import org.springframework.http.HttpStatus

class ErrorResponse(val status: HttpStatus, val msg: String? = null) : Response(status)