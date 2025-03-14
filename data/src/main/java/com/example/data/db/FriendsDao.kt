package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.bean.Friend
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendsDao {
    @Query("SELECT * FROM friend_table WHERE request_id = (:uid) and status = (:userid) ORDER BY id ASC")
    fun getAll(
        uid: Long,
        userid: Long,
    ): Flow<List<Friend>>

    @Insert
    fun insertAll(vararg users: Friend)

    @Delete
    fun delete(user: Friend)
}
