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
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class UpdateUserUseCaseTest {

    @Mock
    private lateinit var mockUserRepository: UserRepositoryImpl

    private lateinit var useCaseUnderTest: UpdateUserUseCase

    val testUsername = "TestUsername"
    val testAge = 12
    val testCity = "TestCity"

    @BeforeEach
    fun setUp() {
        useCaseUnderTest = UpdateUserUseCase(mockUserRepository)
    }


    @Test
    fun `WHEN run UpdateUserUseCase with all parametersn AND user exists THEN save user and return UserResponse`() {
        val fakeInput = UpdateUserUseCase.Input(testUsername, testAge, testCity)
        val fakeUser = User(testUsername, testAge, testCity)
        Mockito.`when`(mockUserRepository.checkIfUserExists(testUsername)).thenReturn(true)
        Mockito.`when`(mockUserRepository.saveUser(fakeUser)).thenReturn(fakeUser)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).checkIfUserExists(testUsername)
        Mockito.verify(mockUserRepository, Mockito.times(1)).saveUser(fakeUser)
        assert(response is UserResponse)
    }

    @Test
    fun `WHEN run UpdateUserUseCase with username only AND user exists THEN save user and return UserResponse`() {
        val fakeInput = UpdateUserUseCase.Input(testUsername)
        val fakeUser = User(testUsername)
        Mockito.`when`(mockUserRepository.checkIfUserExists(testUsername)).thenReturn(true)
        Mockito.`when`(mockUserRepository.saveUser(fakeUser)).thenReturn(fakeUser)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).checkIfUserExists(testUsername)
        Mockito.verify(mockUserRepository, Mockito.times(1)).saveUser(fakeUser)
        assert(response is UserResponse)
    }

    @Test
    fun `WHEN run UpdateUserUseCase with username and age AND user exists THEN save user and return UserResponse`() {
        val fakeInput = UpdateUserUseCase.Input(testUsername, testAge)
        val fakeUser = User(testUsername, testAge)
        Mockito.`when`(mockUserRepository.checkIfUserExists(testUsername)).thenReturn(true)
        Mockito.`when`(mockUserRepository.saveUser(fakeUser)).thenReturn(fakeUser)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).checkIfUserExists(testUsername)
        Mockito.verify(mockUserRepository, Mockito.times(1)).saveUser(fakeUser)
        assert(response is UserResponse)
    }

    @Test
    fun `WHEN run UpdateUserUseCase with username and city AND user exists THEN save user and return UserResponse`() {
        val fakeInput = UpdateUserUseCase.Input(testUsername, city = testCity)
        val fakeUser = User(testUsername, city = testCity)
        Mockito.`when`(mockUserRepository.checkIfUserExists(testUsername)).thenReturn(true)
        Mockito.`when`(mockUserRepository.saveUser(fakeUser)).thenReturn(fakeUser)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).checkIfUserExists(testUsername)
        Mockito.verify(mockUserRepository, Mockito.times(1)).saveUser(fakeUser)
        assert(response is UserResponse)
    }

    @Test
    fun `WHEN run UpdateUserUseCase with username and city AND user exists with age value THEN save user with all 3 properties and return UserResponse`() {
        val otherCity = "otherCity"
        val fakeInput = UpdateUserUseCase.Input(testUsername, city = otherCity)
        val getUser = User(testUsername, testAge, testCity)
        val fakeUser = User(testUsername, testAge, otherCity)
        Mockito.`when`(mockUserRepository.checkIfUserExists(testUsername)).thenReturn(true)
        Mockito.`when`(mockUserRepository.getUser(testUsername)).thenReturn(getUser)
        Mockito.`when`(mockUserRepository.saveUser(fakeUser)).thenReturn(fakeUser)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).checkIfUserExists(testUsername)
        Mockito.verify(mockUserRepository, Mockito.times(1)).getUser(testUsername)
        Mockito.verify(mockUserRepository, Mockito.times(1)).saveUser(fakeUser)
        assert(response is UserResponse)
        assertEquals(fakeUser, (response as UserResponse).user)
    }

    @Test
    fun `WHEN run UpdateUserUseCase AND user does not exist THEN return ErrorResponse`() {
        val fakeInput = UpdateUserUseCase.Input(testUsername)
        val fakeUser = User(testUsername)
        Mockito.`when`(mockUserRepository.checkIfUserExists(testUsername)).thenReturn(false)

        val response = useCaseUnderTest.run(fakeInput)
        Mockito.verify(mockUserRepository, Mockito.times(1)).checkIfUserExists(testUsername)
        Mockito.verify(mockUserRepository, Mockito.never()).saveUser(fakeUser)
        assert(response is ErrorResponse)
    }
}