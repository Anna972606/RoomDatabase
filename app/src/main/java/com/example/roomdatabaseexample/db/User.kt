package com.example.roomdatabaseexample.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo val firstName: String,
    @ColumnInfo val lastName: String
)