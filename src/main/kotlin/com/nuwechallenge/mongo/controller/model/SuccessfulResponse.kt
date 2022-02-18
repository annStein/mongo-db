package com.nuwechallenge.mongo.controller.model

import org.springframework.http.HttpStatus

data class SuccessfulResponse(val status: HttpStatus = HttpStatus.OK) : Response(status)
