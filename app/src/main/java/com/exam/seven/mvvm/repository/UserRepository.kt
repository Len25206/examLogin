package com.exam.seven.mvvm.repository

import com.exam.seven.mvvm.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor() {
    private val userList = mutableListOf<User>()

    fun addUser(user: User) {
        userList.add(user)
    }

    fun getUser(): List<User> = userList
}