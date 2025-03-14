package com.example.data.repository

import androidx.annotation.WorkerThread
import com.example.data.bean.Message
import com.example.data.db.MessageDao
import kotlinx.coroutines.flow.Flow

class MessageRepository(
    val messageDao: MessageDao,
) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(message: Message) {
        messageDao.insertAll(message)
    }

    fun getAllMessage(uid: Long): Flow<List<Message>> = messageDao.getAll(uid)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(message: Message) {
        messageDao.delete(message)
    }
}
