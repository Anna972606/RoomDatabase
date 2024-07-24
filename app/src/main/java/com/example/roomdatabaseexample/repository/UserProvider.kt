package com.example.roomdatabaseexample.repository

import com.example.roomdatabaseexample.db.User

interface UserProvider {

    suspend fun addUser(user: User)

    suspend fun getAllUsers() : List<User>

    suspend fun removeUser(user: User)

    suspend fun getUserByName(firstName: String, lastName: String): List<User>
}