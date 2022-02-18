package com.nuwechallenge.mongo.interactor

import com.nuwechallenge.mongo.controller.model.Response
import com.nuwechallenge.mongo.controller.model.SuccessfulResponse
import com.nuwechallenge.mongo.repository.UserRepositoryImpl
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

/**
 * Class to delete a user.
 */
@Component
class DeleteUserUseCase(val userRepository: UserRepositoryImpl) : BaseInteractor<DeleteUserUseCase.Input> {
    /**
     * Method to delete a user by its username.
     */
    override fun run(input: Input): Response {
        userRepository.deleteUser(input.username)
        return SuccessfulResponse(HttpStatus.NO_CONTENT)
    }

    data class Input(val username: String)
}