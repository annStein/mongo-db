package com.nuwechallenge.mongo.interactor

import com.nuwechallenge.mongo.controller.model.ErrorResponse
import com.nuwechallenge.mongo.controller.model.Response
import com.nuwechallenge.mongo.controller.model.ResponseMessage
import com.nuwechallenge.mongo.controller.model.UserResponse
import com.nuwechallenge.mongo.data.model.User
import com.nuwechallenge.mongo.repository.UserRepositoryImpl
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

/**
 * Class to create a User.
 */
@Component
class CreateUserUseCase(val userRepository: UserRepositoryImpl) : BaseInteractor<CreateUserUseCase.Input> {
    /**
     * Method to create a user. User is only created if the username doesn't exist in the database.
     */
    override fun run(input: Input): Response {
        return if (!userRepository.checkIfUserExists(input.username)) {
            val user = User(input.username, input.age, input.city)
            val result = userRepository.saveUser(user)
            UserResponse(result)
        } else {
            ErrorResponse(HttpStatus.CONFLICT, ResponseMessage.USER_EXISTS)
        }
    }

    data class Input(
        val username: String,
        val age: Int? = null,
        val city: String? = null
    )
}

