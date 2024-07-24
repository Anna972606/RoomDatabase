package com.example.roomdatabaseexample.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao{
    @Query("Select * from user order by id desc")
    fun getAllUsers() : List<User>

    @Query("select * from user where firstName like :first and lastName like :last")
    fun getUserByName(first: String, last: String) : List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Delete
    fun deleteUser(user: User)
}