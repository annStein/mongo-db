package com.nuwechallenge.mongo.interactor

import com.nuwechallenge.mongo.controller.model.AppException
import com.nuwechallenge.mongo.controller.model.ErrorResponse
import com.nuwechallenge.mongo.controller.model.ExceptionType
import com.nuwechallenge.mongo.controller.model.UserResponse
import com.nuwechallenge.mongo.data.model.User
import com.nuwechallenge.mongo.repository.UserRepositoryImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GetUserUseCaseTest {

    @Mock
    private lateinit var mockUserRepository: UserRepositoryImpl

    private lateinit var useCaseUnderTest: GetUserUseCase

    val testUsername = "TestUsername"
    val testAge = 12
    val testCity = "TestCity"

    @BeforeEach
    fun setUp() {
        useCaseUnderTest = GetUserUseCase(mockUserRepository)
    }


    @Test
    fun `WHEN run GetUserUseCase AND user exists THEN return UserResponse`() {
        val fakeInput = GetUserUseCase.Input(testUsername)
        val fakeUser = User(testUsername, testAge, testCity)
        Mockito.`when`(mockUserRepository.getUser(testUsername)).thenReturn(fakeUser)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).getUser(testUsername)
        assert(response is UserResponse)
    }

    @Test
    fun `WHEN invoke GetUserUseCase AND an error occurs THEN return ErrorResponse`() {
        val fakeInput = GetUserUseCase.Input(testUsername)
        Mockito.`when`(mockUserRepository.getUser(testUsername)).thenThrow(AppException(ExceptionType.USER_NOT_FOUND))

        val response = useCaseUnderTest.invoke(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).getUser(testUsername)
        assert(response is ErrorResponse)
    }
}