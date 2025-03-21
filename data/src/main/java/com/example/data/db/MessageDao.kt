package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.bean.Message
import com.example.data.bean.MessageItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query("SELECT * FROM message_table WHERE accept_id = (:uid) ORDER BY id ASC")
    fun getAll(uid: Long): Flow<List<Message>>

    @Insert
    fun insertAll(vararg users: Message)

    @Delete
    fun delete(user: Message)
}

@Dao
interface MessageListDao {
    @Query("SELECT * FROM message_list WHERE accept_id = (:uid) ORDER BY update_time ASC")
    fun getMessageList(uid: Long): Flow<List<MessageItem>>
}
