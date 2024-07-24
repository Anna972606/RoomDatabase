package com.example.roomdatabaseexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabaseexample.model.UserUiModel
import com.example.roomdatabaseexample.repository.UserRepository
import com.example.roomdatabaseexample.db.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userUiModelEvent = MutableLiveData<List<UserUiModel>>()
    val userUiModelEvent: LiveData<List<UserUiModel>> = _userUiModelEvent

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()

    init {
        getAllUsers()
    }

    fun addUser() {
        if (firstName.value.isNullOrEmpty() && lastName.value.isNullOrEmpty()) return
        // Create a new coroutine on the IO thread
        viewModelScope.launch(Dispatchers.IO) {
            // Make the add User call and suspend execution until it finishes
            userRepository.addUser(
                user = User(
                    firstName = firstName.value.orEmpty(),
                    lastName = lastName.value.orEmpty()
                )
            )
            // get All users after adding user to room database
            getAllUsers()
            // switch the execution context of the coroutine to the Dispatchers.Main
            withContext(Dispatchers.Main){
                firstName.value = ""
                lastName.value = ""
            }
        }
    }

    private fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            renderUsers(userRepository.getAllUsers())
        }
    }

    private fun removeUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.removeUser(user)
            getAllUsers()
        }
    }

    fun searchUserByName() {
        if (firstName.value.isNullOrEmpty() && lastName.value.isNullOrEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            val users = userRepository.getUserByName(
                firstName = firstName.value.orEmpty(),
                lastName = lastName.value.orEmpty()
            )
            renderUsers(users)
        }
    }

    private fun renderUsers(users: List<User>) {
        _userUiModelEvent.postValue(users.map {
            UserUiModel(
                id = it.id,
                name = it.firstName + " " + it.lastName,
                onRemove = {
                    removeUser(it)
                }
            )
        })
    }
}