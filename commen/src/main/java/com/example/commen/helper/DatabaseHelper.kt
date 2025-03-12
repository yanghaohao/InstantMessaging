package com.example.commen.helper

import android.content.Context
import com.example.data.database.FriendsRoomDatabase
import com.example.data.repository.FriendsRepository
import com.example.data.repository.MessageItemRepository
import com.example.data.repository.MessageRepository
import com.example.data.repository.MineRepository

class DatabaseHelper(val context: Context) {
    val database by lazy {
        FriendsRoomDatabase.getDatabase(context)
    }
    val friendsRepository by lazy {
        FriendsRepository(database.friendsDao())
    }
    val messageRepository by lazy {
        MessageRepository(database.messageDao())
    }
    val messageListRepository by lazy {
        MessageItemRepository(database.messageListDao())
    }
    val mineRepository by lazy {
        MineRepository(database.userDao())
    }

    companion object {
        fun instance(context: Context):DatabaseHelper{
            return DatabaseHelper(context)
        }
    }
}