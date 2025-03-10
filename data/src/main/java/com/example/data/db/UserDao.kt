package com.example.data.db

import androidx.room.Query
import com.example.data.bean.Mine

interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getUser(): List<Mine>
}
