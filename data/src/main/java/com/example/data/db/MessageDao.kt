package com.example.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.bean.Message
import com.example.data.bean.MessageItem

interface MessageDao {
    @Query("SELECT accept_id = (:uid) FROM message_table")
    fun getAll(uid: Long): List<Message>

    @Insert
    fun insertAll(vararg users: Message)

    @Delete
    fun delete(user: Message)
}

interface MessageListDao {
    @Query("SELECT accept_id = (:uid) FROM message_list")
    fun getMessageList(uid: Long): List<MessageItem>
}
