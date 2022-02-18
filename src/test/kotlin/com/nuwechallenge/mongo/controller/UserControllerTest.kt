package com.nuwechallenge.mongo.controller

import com.nuwechallenge.mongo.controller.model.ErrorResponse
import com.nuwechallenge.mongo.controller.model.SuccessfulResponse
import com.nuwechallenge.mongo.controller.model.UserResponse
import com.nuwechallenge.mongo.data.model.User
import com.nuwechallenge.mongo.interactor.CreateUserUseCase
import com.nuwechallenge.mongo.interactor.DeleteUserUseCase
import com.nuwechallenge.mongo.interactor.GetUserUseCase
import com.nuwechallenge.mongo.interactor.UpdateUserUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class UserControllerTest {

    @Mock
    private lateinit var mockCreateUserUseCase: CreateUserUseCase

    @Mock
    private lateinit var mockDeleteUserUseCase: DeleteUserUseCase

    @Mock
    private lateinit var mockGetUserUseCase: GetUserUseCase

    @Mock
    private lateinit var mockUpdateUserUseCase: UpdateUserUseCase

    private lateinit var controllerUnderTest: UserController

    val testUsername = "testname"
    val testAge = 12
    val testCity = "Testcity"

    @BeforeEach
    fun setup() {
        controllerUnderTest =
            UserController(mockCreateUserUseCase, mockDeleteUserUseCase, mockGetUserUseCase, mockUpdateUserUseCase)
    }

    @Test
    fun `WHEN createUser is called with all parameters THEN CreateUserUseCase is called and UserResponse is returned`() {
        val fakeUserInput = CreateUserUseCase.Input(testUsername, testAge, testCity)
        val fakeUser = User(testUsername, testAge, testCity)
        Mockito.`when`(mockCreateUserUseCase.invoke(fakeUserInput)).thenReturn(UserResponse(fakeUser))

        val response = controllerUnderTest.createUser(testUsername, testAge, testCity)
        Mockito.verify(mockCreateUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is UserResponse)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `WHEN createUser is called with username only THEN CreateUserUseCase is called and UserResponse is returned`() {
        val fakeUserInput = CreateUserUseCase.Input(testUsername)
        val fakeUser = User(testUsername)
        Mockito.`when`(mockCreateUserUseCase.invoke(fakeUserInput)).thenReturn(UserResponse(fakeUser))

        val response = controllerUnderTest.createUser(testUsername)
        Mockito.verify(mockCreateUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is UserResponse)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `WHEN createUser is called with username and age parameters THEN CreateUserUseCase is called and UserResponse is returned`() {
        val fakeUserInput = CreateUserUseCase.Input(testUsername, testAge)
        val fakeUser = User(testUsername, testAge)
        Mockito.`when`(mockCreateUserUseCase.invoke(fakeUserInput)).thenReturn(UserResponse(fakeUser))

        val response = controllerUnderTest.createUser(testUsername, testAge)
        Mockito.verify(mockCreateUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is UserResponse)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `WHEN createUser is called with username and city parameters THEN CreateUserUseCase is called and UserResponse is returned`() {
        val fakeUserInput = CreateUserUseCase.Input(testUsername, city = testCity)
        val fakeUser = User(testUsername, city = testCity)
        Mockito.`when`(mockCreateUserUseCase.invoke(fakeUserInput)).thenReturn(UserResponse(fakeUser))

        val response = controllerUnderTest.createUser(testUsername, city = testCity)
        Mockito.verify(mockCreateUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is UserResponse)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `WHEN createUser is called AND error occurs THEN ErrorReponse is returned`() {
        val fakeUserInput = CreateUserUseCase.Input(testUsername, city = testCity)
        Mockito.`when`(mockCreateUserUseCase.invoke(fakeUserInput)).thenReturn(ErrorResponse(HttpStatus.BAD_GATEWAY))

        val response = controllerUnderTest.createUser(testUsername, city = testCity)
        assertTrue(response.body is ErrorResponse)
        assertEquals(HttpStatus.BAD_GATEWAY, response.statusCode)
    }

    @Test
    fun `WHEN getUser is called THEN GetUserUseCase is called and UserResponse is returned`() {
        val fakeUserInput = GetUserUseCase.Input(testUsername)
        val fakeUser = User(testUsername)
        Mockito.`when`(mockGetUserUseCase.invoke(fakeUserInput)).thenReturn(UserResponse(fakeUser))

        val response = controllerUnderTest.getUser(testUsername)
        Mockito.verify(mockGetUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is UserResponse)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `WHEN getUser is called AND an error occurs THEN ErrorResponse is returned`() {
        val fakeUserInput = GetUserUseCase.Input(testUsername)
        Mockito.`when`(mockGetUserUseCase.invoke(fakeUserInput)).thenReturn(ErrorResponse(HttpStatus.NOT_FOUND))

        val response = controllerUnderTest.getUser(testUsername)
        Mockito.verify(mockGetUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is ErrorResponse)
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun `WHEN deleteUser is called THEN DeleteUserUseCase is called`() {
        val fakeUserInput = DeleteUserUseCase.Input(testUsername)
        Mockito.`when`(mockDeleteUserUseCase.invoke(fakeUserInput))
            .thenReturn(SuccessfulResponse(HttpStatus.NO_CONTENT))

        val response = controllerUnderTest.deleteUser(testUsername)
        Mockito.verify(mockDeleteUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is SuccessfulResponse)
        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }

    @Test
    fun `WHEN deleteUser is called AND an error occurs THEN an error response is returned`() {
        val fakeUserInput = DeleteUserUseCase.Input(testUsername)
        Mockito.`when`(mockDeleteUserUseCase.invoke(fakeUserInput)).thenReturn(ErrorResponse(HttpStatus.BAD_GATEWAY))

        val response = controllerUnderTest.deleteUser(testUsername)
        Mockito.verify(mockDeleteUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is ErrorResponse)
        assertEquals(HttpStatus.BAD_GATEWAY, response.statusCode)
    }

    @Test
    fun `WHEN updateUser is called with username only THEN UpdateUserUseCase is called and a UserResponse is returned`() {
        val fakeUserInput = UpdateUserUseCase.Input(testUsername)
        val fakeUser = User(testUsername)
        Mockito.`when`(mockUpdateUserUseCase.invoke(fakeUserInput)).thenReturn(UserResponse(fakeUser, HttpStatus.OK))

        val response = controllerUnderTest.updateUser(testUsername)
        Mockito.verify(mockUpdateUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is UserResponse)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `WHEN updateUser is called with username and age THEN UpdateUserUseCase is called and a UserResponse is returned`() {
        val fakeUserInput = UpdateUserUseCase.Input(testUsername, testAge)
        val fakeUser = User(testUsername, testAge)
        Mockito.`when`(mockUpdateUserUseCase.invoke(fakeUserInput)).thenReturn(UserResponse(fakeUser, HttpStatus.OK))

        val response = controllerUnderTest.updateUser(testUsername, testAge)
        Mockito.verify(mockUpdateUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is UserResponse)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `WHEN updateUser is called with username and city THEN UpdateUserUseCase is called`() {
        val fakeUserInput = UpdateUserUseCase.Input(testUsername, city = testCity)
        val fakeUser = User(testUsername, city = testCity)
        Mockito.`when`(mockUpdateUserUseCase.invoke(fakeUserInput)).thenReturn(UserResponse(fakeUser, HttpStatus.OK))

        val response = controllerUnderTest.updateUser(testUsername, city = testCity)
        Mockito.verify(mockUpdateUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is UserResponse)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `WHEN updateUser is called with username, age and city THEN UpdateUserUseCase is called`() {
        val fakeUserInput = UpdateUserUseCase.Input(testUsername, testAge, testCity)
        val fakeUser = User(testUsername, testAge, testCity)
        Mockito.`when`(mockUpdateUserUseCase.invoke(fakeUserInput)).thenReturn(UserResponse(fakeUser, HttpStatus.OK))

        val response = controllerUnderTest.updateUser(testUsername, testAge, testCity)
        Mockito.verify(mockUpdateUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is UserResponse)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `WHEN updateUser is called AND an Error occurs THEN an ErrorResponse is returned`() {
        val fakeUserInput = UpdateUserUseCase.Input(testUsername, testAge, testCity)
        Mockito.`when`(mockUpdateUserUseCase.invoke(fakeUserInput)).thenReturn(ErrorResponse(HttpStatus.BAD_GATEWAY))

        val response = controllerUnderTest.updateUser(testUsername, testAge, testCity)
        Mockito.verify(mockUpdateUserUseCase, Mockito.times(1)).invoke(fakeUserInput)
        assertTrue(response.body is ErrorResponse)
        assertEquals(HttpStatus.BAD_GATEWAY, response.statusCode)
    }
}