package com.example.roomdatabaseexample.model

data class UserUiModel(
    val id: Int,
    val name: String,
    val onRemove: (() ->Unit)
)