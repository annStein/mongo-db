package com.nuwechallenge.mongo.interactor

import com.nuwechallenge.mongo.controller.model.SuccessfulResponse
import com.nuwechallenge.mongo.repository.UserRepositoryImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class DeleteUserUseCaseTest {

    @Mock
    private lateinit var mockUserRepository: UserRepositoryImpl

    private lateinit var useCaseUnderTest: DeleteUserUseCase

    val testUsername = "TestUsername"

    @BeforeEach
    fun setUp() {
        useCaseUnderTest = DeleteUserUseCase(mockUserRepository)
    }

    @Test
    fun `WHEN run deleteUserUseCase AND user exists THEN delete user and return SuccessfulReponse with NoContent code`() {
        val fakeInput = DeleteUserUseCase.Input(testUsername)
        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).deleteUser(testUsername)
        assert(response is SuccessfulResponse)
        assertEquals(HttpStatus.NO_CONTENT, (response as SuccessfulResponse).httpStatus)
    }
}