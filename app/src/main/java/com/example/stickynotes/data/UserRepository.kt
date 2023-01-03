package com.example.stickynotes.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {


    val readAllData: LiveData<List<User>> = userDao.realAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }
    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
}