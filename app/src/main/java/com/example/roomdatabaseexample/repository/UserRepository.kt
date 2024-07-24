package com.example.roomdatabaseexample.repository

import com.example.roomdatabaseexample.db.User
import com.example.roomdatabaseexample.db.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
): UserProvider {

    override suspend fun addUser(user: User) {
        userDao.addUser(user = user)
    }

    override suspend fun getAllUsers()  = userDao.getAllUsers()

    override suspend fun removeUser(user: User)  = userDao.deleteUser(user = user)

    override suspend fun getUserByName(firstName: String, lastName: String) =
        userDao.getUserByName(first = firstName, last = lastName)
}