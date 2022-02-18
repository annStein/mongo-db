package com.nuwechallenge.mongo.repository

import com.nuwechallenge.mongo.controller.model.AppException
import com.nuwechallenge.mongo.controller.model.ExceptionType
import com.nuwechallenge.mongo.data.UserMongoDBDatasource
import com.nuwechallenge.mongo.data.model.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class UserRepositoryImplTest {
    @Mock
    private lateinit var mockUserDatasource: UserMongoDBDatasource

    private lateinit var repositoryUnderTest: UserRepositoryImpl

    val testUsername = "TestUsername"
    val testAge = 12
    val testCity = "TestCity"

    @BeforeEach
    fun setUp() {
        repositoryUnderTest = UserRepositoryImpl(mockUserDatasource)
    }

    @Test
    fun `WHEN saveUser() with all parameters THEN call datasource to save user`() {
        val fakeUser = User(testUsername, testAge, testCity)
        Mockito.`when`(mockUserDatasource.saveUser(fakeUser)).thenReturn(fakeUser)

        val result = repositoryUnderTest.saveUser(fakeUser)
        Mockito.verify(mockUserDatasource, Mockito.times(1)).saveUser(fakeUser)
        assertEquals(fakeUser, result)
    }

    @Test
    fun `WHEN saveUser() with username and age THEN call datasource to save user`() {
        val fakeUser = User(testUsername, testAge)
        Mockito.`when`(mockUserDatasource.saveUser(fakeUser)).thenReturn(fakeUser)

        val result = repositoryUnderTest.saveUser(fakeUser)
        Mockito.verify(mockUserDatasource, Mockito.times(1)).saveUser(fakeUser)
        assertEquals(fakeUser, result)
    }

    @Test
    fun `WHEN saveUser() with username and city THEN call datasource to save user`() {
        val fakeUser = User(testUsername, city = testCity)
        Mockito.`when`(mockUserDatasource.saveUser(fakeUser)).thenReturn(fakeUser)

        val result = repositoryUnderTest.saveUser(fakeUser)
        Mockito.verify(mockUserDatasource, Mockito.times(1)).saveUser(fakeUser)
        assertEquals(fakeUser, result)
    }

    @Test
    fun `WHEN saveUser() with username THEN call datasource to save user`() {
        val fakeUser = User(testUsername)
        Mockito.`when`(mockUserDatasource.saveUser(fakeUser)).thenReturn(fakeUser)

        val result = repositoryUnderTest.saveUser(fakeUser)
        Mockito.verify(mockUserDatasource, Mockito.times(1)).saveUser(fakeUser)
        assertEquals(fakeUser, result)
    }

    @Test
    fun `WHEN checkIfUserExists() with username AND user exists THEN call datasource and return true`() {
        Mockito.`when`(mockUserDatasource.checkIfUserExists(testUsername)).thenReturn(true)

        val result = repositoryUnderTest.checkIfUserExists(testUsername)
        Mockito.verify(mockUserDatasource, Mockito.times(1)).checkIfUserExists(testUsername)
        assertTrue(result)
    }

    @Test
    fun `WHEN checkIfUserExists() with username AND user does not exist THEN call datasource and return false`() {
        Mockito.`when`(mockUserDatasource.checkIfUserExists(testUsername)).thenReturn(false)

        val result = repositoryUnderTest.checkIfUserExists(testUsername)
        Mockito.verify(mockUserDatasource, Mockito.times(1)).checkIfUserExists(testUsername)
        assertFalse(result)
    }

    @Test
    fun `WHEN getUser() with username AND user exists THEN return user`() {
        val fakeUser = User(testUsername, testAge, testCity)
        Mockito.`when`(mockUserDatasource.getUser(testUsername)).thenReturn(fakeUser)

        val result = repositoryUnderTest.getUser(testUsername)
        Mockito.verify(mockUserDatasource, Mockito.times(1)).getUser(testUsername)
        assertEquals(fakeUser, result)
    }

    @Test
    fun `WHEN getUser() with username AND user does not exist THEN throw AppException`() {
        Mockito.`when`(mockUserDatasource.getUser(testUsername)).thenThrow(AppException(ExceptionType.USER_NOT_FOUND))
        assertThrows<AppException> {
            repositoryUnderTest.getUser(testUsername)
        }
        Mockito.verify(mockUserDatasource, Mockito.times(1)).getUser(testUsername)
    }

    @Test
    fun `WHEN deleteUser() with username THEN call datasource`() {
        repositoryUnderTest.deleteUser(testUsername)
        Mockito.verify(mockUserDatasource, Mockito.times(1)).deleteUser(testUsername)
    }
}