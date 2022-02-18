package com.nuwechallenge.mongo.interactor

import com.nuwechallenge.mongo.controller.model.AppException
import com.nuwechallenge.mongo.controller.model.ExceptionType
import com.nuwechallenge.mongo.controller.model.Response
import com.nuwechallenge.mongo.controller.model.UserResponse
import com.nuwechallenge.mongo.repository.UserRepositoryImpl
import org.springframework.stereotype.Component

/**
 * Class to get a user.
 */
@Component
class GetUserUseCase(val userRepository: UserRepositoryImpl) : BaseInteractor<GetUserUseCase.Input> {
    /**
     * Method to get a user by its id.
     */
    override fun run(input: Input): Response {
        val user = userRepository.getUser(input.username)
        return if (user != null)
            UserResponse(user)
        else throw AppException(ExceptionType.USER_NOT_FOUND)
    }

    data class Input(val username: String)
}