package com.example.data.repository

import androidx.annotation.WorkerThread
import com.example.data.bean.Friend
import com.example.data.db.FriendsDao
import kotlinx.coroutines.flow.Flow

class FriendsRepository(
    private val friendsDao: FriendsDao,
) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(friend: Friend) {
        friendsDao.insertAll(friend)
    }

    fun getAllFriends(uid: Long): Flow<List<Friend>> = friendsDao.getAll(uid)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(friend: Friend) {
        friendsDao.delete(friend)
    }
}
