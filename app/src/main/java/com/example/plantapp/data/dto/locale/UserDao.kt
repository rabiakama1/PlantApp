package com.example.plantapp.data.dto.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {
    @Query("SELECT * FROM USER")
    suspend fun getUserOption(): User?

    @Insert
    suspend fun insertUser(vararg user: User)

}