package com.nuwechallenge.mongo.controller

import com.nuwechallenge.mongo.controller.model.Response
import com.nuwechallenge.mongo.interactor.CreateUserUseCase
import com.nuwechallenge.mongo.interactor.DeleteUserUseCase
import com.nuwechallenge.mongo.interactor.GetUserUseCase
import com.nuwechallenge.mongo.interactor.UpdateUserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/user")
class UserController(
    val createUserUseCase: CreateUserUseCase,
    val deleteUserUseCase: DeleteUserUseCase,
    val getUserUseCase: GetUserUseCase,
    val updateUserUseCase: UpdateUserUseCase
) {

    @PostMapping
    @ResponseBody
    fun createUser(
        @RequestParam(required = true) username: String,
        @RequestParam(required = false) age: Int? = null,
        @RequestParam(required = false) city: String? = null
    ): ResponseEntity<Response> {
        val result = createUserUseCase.invoke(CreateUserUseCase.Input(username, age, city))
        return createResponseEntity(result)
    }

    @DeleteMapping
    @ResponseBody
    fun deleteUser(
        @RequestParam(required = true) username: String
    ): ResponseEntity<Response> {
        val result = deleteUserUseCase.invoke(DeleteUserUseCase.Input(username))
        return createResponseEntity(result)
    }

    @GetMapping
    @ResponseBody
    fun getUser(
        @RequestParam(required = true) username: String
    ): ResponseEntity<Response> {
        val result = getUserUseCase.invoke(GetUserUseCase.Input(username))
        return createResponseEntity(result)
    }

    @PutMapping
    @ResponseBody
    fun updateUser(
        @RequestParam(required = true) username: String,
        @RequestParam(required = false) age: Int? = null,
        @RequestParam(required = false) city: String? = null
    ): ResponseEntity<Response> {
        val result = updateUserUseCase.invoke(UpdateUserUseCase.Input(username, age, city))
        return createResponseEntity(result)
    }

    private fun createResponseEntity(response: Response): ResponseEntity<Response> =
        ResponseEntity(response, response.httpStatus)
}