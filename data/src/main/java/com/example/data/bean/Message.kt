package com.example.data.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table")
data class Message(
    @PrimaryKey
    val id: Long,
    val content: String,
    @ColumnInfo(name = "request_id")
    val requestId: Long,
    @ColumnInfo(name = "accept_id")
    val acceptId: Long,
    @ColumnInfo(name = "is_read")
    val isRead: Boolean,
)

@Entity(tableName = "message_List")
data class MessageItem(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "accept_id")
    val acceptId: Long,
)
