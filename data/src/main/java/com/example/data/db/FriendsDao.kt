package com.example.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.bean.Friend

interface FriendsDao {
    @Query("SELECT request_id = (:uid) and status = 'accepted' FROM friend_table")
    fun getAll(uid: Long): List<Friend>

    @Insert
    fun insertAll(vararg users: Friend)

    @Delete
    fun delete(user: Friend)
}
