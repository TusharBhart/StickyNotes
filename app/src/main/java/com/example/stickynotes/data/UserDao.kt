package com.example.stickynotes.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("delete from user_table")
    suspend fun deleteAllUser()


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun realAllData():LiveData<List<User>>
}