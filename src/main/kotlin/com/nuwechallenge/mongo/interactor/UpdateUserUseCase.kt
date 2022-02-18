package com.nuwechallenge.mongo.interactor

import com.nuwechallenge.mongo.controller.model.ErrorResponse
import com.nuwechallenge.mongo.controller.model.Response
import com.nuwechallenge.mongo.controller.model.ResponseMessage
import com.nuwechallenge.mongo.controller.model.UserResponse
import com.nuwechallenge.mongo.data.model.User
import com.nuwechallenge.mongo.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

/**
 * Class to update a user.
 */
@Component
class UpdateUserUseCase(val userRepository: UserRepository) : BaseInteractor<UpdateUserUseCase.Input> {
    /**
     * Method to update a user. If the User doesn't exist an ErrorResponse is returned.
     * Only properties that are not null will be updated.
     */
    override fun run(input: Input): Response {
        return if (userRepository.checkIfUserExists(input.username)) {
            val user = userRepository.getUser(input.username)
            // update only not null properties
            val updateUser = User(input.username, input.age ?: user?.age, input.city ?: user?.city)
            val result = userRepository.saveUser(updateUser)
            UserResponse(result)
        } else {
            ErrorResponse(HttpStatus.NOT_FOUND, ResponseMessage.USER_NOT_FOUND)
        }
    }

    data class Input(
        val username: String,
        val age: Int? = null,
        val city: String? = null
    )
}

