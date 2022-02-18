package com.nuwechallenge.mongo.interactor

import com.nuwechallenge.mongo.controller.model.AppException
import com.nuwechallenge.mongo.controller.model.ErrorResponse
import com.nuwechallenge.mongo.controller.model.Response

interface BaseInteractor<Input> {
    fun run(input: Input): Response

    fun invoke(input: Input): Response {
        return try {
            run(input)
        } catch (e: AppException) {
            e.type.let {
                return ErrorResponse(it.statusCode, it.msg)
            }
        }
    }
}