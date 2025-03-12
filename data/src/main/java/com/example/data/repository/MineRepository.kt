package com.example.data.repository

import com.example.data.bean.Mine
import com.example.data.db.UserDao
import kotlinx.coroutines.flow.Flow

class MineRepository(
    val userDao: UserDao,
) {
    fun getAllFriends(): Flow<List<Mine>> = userDao.getUser()
}
