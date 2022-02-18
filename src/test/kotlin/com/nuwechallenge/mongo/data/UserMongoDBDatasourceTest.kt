package com.nuwechallenge.mongo.data

import com.nuwechallenge.mongo.controller.model.AppException
import com.nuwechallenge.mongo.data.model.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserMongoDBDatasourceTest {
    @Mock
    private lateinit var mockMongoDBDatasource: MongoDBDatasource

    private lateinit var datasourceUnderTest: UserMongoDBDatasource

    val testUsername = "TestUsername"
    val testAge = 12
    val testCity = "TestCity"

    @BeforeEach
    fun setUp() {
        datasourceUnderTest = UserMongoDBDatasource(mockMongoDBDatasource)
    }

    @Test
    fun `WHEN saveUser() with all parameters THEN save user`() {
        val fakeUser = User(testUsername, testAge, testCity)
        Mockito.`when`(mockMongoDBDatasource.save(fakeUser)).thenReturn(fakeUser)

        val result = datasourceUnderTest.saveUser(fakeUser)
        Mockito.verify(mockMongoDBDatasource, Mockito.times(1)).save(fakeUser)
        assertEquals(fakeUser, result)
    }

    @Test
    fun `WHEN saveUser() with username and age THEN save user`() {
        val fakeUser = User(testUsername, testAge)
        Mockito.`when`(mockMongoDBDatasource.save(fakeUser)).thenReturn(fakeUser)

        val result = datasourceUnderTest.saveUser(fakeUser)
        Mockito.verify(mockMongoDBDatasource, Mockito.times(1)).save(fakeUser)
        assertEquals(fakeUser, result)
    }

    @Test
    fun `WHEN saveUser() with username and city THEN save user`() {
        val fakeUser = User(testUsername, city = testCity)
        Mockito.`when`(mockMongoDBDatasource.save(fakeUser)).thenReturn(fakeUser)

        val result = datasourceUnderTest.saveUser(fakeUser)
        Mockito.verify(mockMongoDBDatasource, Mockito.times(1)).save(fakeUser)
        assertEquals(fakeUser, result)
    }

    @Test
    fun `WHEN saveUser() with username THEN save user`() {
        val fakeUser = User(testUsername)
        Mockito.`when`(mockMongoDBDatasource.save(fakeUser)).thenReturn(fakeUser)

        val result = datasourceUnderTest.saveUser(fakeUser)
        Mockito.verify(mockMongoDBDatasource, Mockito.times(1)).save(fakeUser)
        assertEquals(fakeUser, result)
    }

    @Test
    fun `WHEN checkIfUserExists() with username AND user exists THEN return true`() {
        Mockito.`when`(mockMongoDBDatasource.existsById(testUsername)).thenReturn(true)

        val result = datasourceUnderTest.checkIfUserExists(testUsername)
        Mockito.verify(mockMongoDBDatasource, Mockito.times(1)).existsById(testUsername)
        kotlin.test.assertTrue(result)
    }

    @Test
    fun `WHEN checkIfUserExists() with username AND user does not exist THEN return false`() {
        Mockito.`when`(mockMongoDBDatasource.existsById(testUsername)).thenReturn(false)

        val result = datasourceUnderTest.checkIfUserExists(testUsername)
        Mockito.verify(mockMongoDBDatasource, Mockito.times(1)).existsById(testUsername)
        kotlin.test.assertFalse(result)
    }

    @Test
    fun `WHEN getUser() with username AND user exists THEN return user`() {
        val fakeUser = User(testUsername, testAge, testCity)
        Mockito.`when`(mockMongoDBDatasource.findById(testUsername)).thenReturn(Optional.of(fakeUser))

        val result = datasourceUnderTest.getUser(testUsername)
        Mockito.verify(mockMongoDBDatasource, Mockito.times(1)).findById(testUsername)
        assertEquals(fakeUser, result)
    }

    @Test
    fun `WHEN getUser() with username AND user does not exist THEN return AppException`() {
        Mockito.`when`(mockMongoDBDatasource.findById(testUsername)).thenReturn(Optional.empty())
        org.junit.jupiter.api.assertThrows<AppException> {
            datasourceUnderTest.getUser(testUsername)
        }
        Mockito.verify(mockMongoDBDatasource, Mockito.times(1)).findById(testUsername)
    }

    @Test
    fun `WHEN deleteUser() with username THEN call datasource`() {
        datasourceUnderTest.deleteUser(testUsername)
        Mockito.verify(mockMongoDBDatasource, Mockito.times(1)).deleteById(testUsername)
    }
}