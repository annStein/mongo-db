package com.nuwechallenge.mongo.interactor

import com.nuwechallenge.mongo.controller.model.ErrorResponse
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
class CreateUserUseCaseTest {

    @Mock
    private lateinit var mockUserRepository: UserRepositoryImpl

    private lateinit var useCaseUnderTest: CreateUserUseCase

    val testUsername = "TestUsername"
    val testAge = 12
    val testCity = "TestCity"

    @BeforeEach
    fun setUp() {
        useCaseUnderTest = CreateUserUseCase(mockUserRepository)
    }


    @Test
    fun `WHEN run createUserUseCase with all parameters THEN save user and return UserResponse`() {
        val fakeInput = CreateUserUseCase.Input(testUsername, testAge, testCity)
        val fakeUser = User(testUsername, testAge, testCity)
        Mockito.`when`(mockUserRepository.checkIfUserExists(testUsername)).thenReturn(false)
        Mockito.`when`(mockUserRepository.saveUser(fakeUser)).thenReturn(fakeUser)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).checkIfUserExists(testUsername)
        Mockito.verify(mockUserRepository, Mockito.times(1)).saveUser(fakeUser)
        assert(response is UserResponse)
    }

    @Test
    fun `WHEN run createUserUseCase with username only THEN save user and return UserResponse`() {
        val fakeInput = CreateUserUseCase.Input(testUsername)
        val fakeUser = User(testUsername)
        Mockito.`when`(mockUserRepository.checkIfUserExists(testUsername)).thenReturn(false)
        Mockito.`when`(mockUserRepository.saveUser(fakeUser)).thenReturn(fakeUser)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).checkIfUserExists(testUsername)
        Mockito.verify(mockUserRepository, Mockito.times(1)).saveUser(fakeUser)
        assert(response is UserResponse)
    }

    @Test
    fun `WHEN run createUserUseCase with username and age only THEN save user and return UserResponse`() {
        val fakeInput = CreateUserUseCase.Input(testUsername, testAge)
        val fakeUser = User(testUsername, testAge)
        Mockito.`when`(mockUserRepository.checkIfUserExists(testUsername)).thenReturn(false)
        Mockito.`when`(mockUserRepository.saveUser(fakeUser)).thenReturn(fakeUser)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).checkIfUserExists(testUsername)
        Mockito.verify(mockUserRepository, Mockito.times(1)).saveUser(fakeUser)
        assert(response is UserResponse)
    }

    @Test
    fun `WHEN run createUserUseCase with username and city only THEN save user and return UserResponse`() {
        val fakeInput = CreateUserUseCase.Input(testUsername, city = testCity)
        val fakeUser = User(testUsername, city = testCity)
        Mockito.`when`(mockUserRepository.checkIfUserExists(testUsername)).thenReturn(false)
        Mockito.`when`(mockUserRepository.saveUser(fakeUser)).thenReturn(fakeUser)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).checkIfUserExists(testUsername)
        Mockito.verify(mockUserRepository, Mockito.times(1)).saveUser(fakeUser)
        assert(response is UserResponse)
    }

    @Test
    fun `WHEN run createUserUseCase AND user exists already THEN return ErrorResponse`() {
        val fakeInput = CreateUserUseCase.Input(testUsername)
        val fakeUser = User(testUsername)
        Mockito.`when`(mockUserRepository.checkIfUserExists(testUsername)).thenReturn(true)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).checkIfUserExists(testUsername)
        Mockito.verify(mockUserRepository, Mockito.never()).saveUser(fakeUser)
        assert(response is ErrorResponse)
    }
}