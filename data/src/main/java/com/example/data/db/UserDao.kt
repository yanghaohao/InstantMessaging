package com.example.data.db

import androidx.room.Dao
import androidx.room.Query
import com.example.data.bean.Mine
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getUser(): Flow<List<Mine>>
}
