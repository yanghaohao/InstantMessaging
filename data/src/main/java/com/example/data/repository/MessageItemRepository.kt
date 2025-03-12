package com.example.data.repository

import com.example.data.bean.MessageItem
import com.example.data.db.MessageListDao
import kotlinx.coroutines.flow.Flow

class MessageItemRepository(
    val messageListDao: MessageListDao,
) {
    fun getAllMessageItem(uid: Long): Flow<List<MessageItem>> = messageListDao.getMessageList(uid)
}
