package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.bean.Friend
import com.example.data.bean.Message
import com.example.data.bean.MessageItem
import com.example.data.bean.Mine
import com.example.data.db.FriendsDao
import com.example.data.db.MessageDao
import com.example.data.db.MessageListDao
import com.example.data.db.UserDao

@Database(
    entities = [Friend::class, Message::class, MessageItem::class, Mine::class],
    version = 1,
    exportSchema = false,
)
abstract class FriendsRoomDatabase : RoomDatabase() {
    abstract fun friendsDao(): FriendsDao

    abstract fun messageDao(): MessageDao

    abstract fun messageListDao(): MessageListDao

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: FriendsRoomDatabase? = null

        fun getDatabase(context: Context): FriendsRoomDatabase =
            INSTANCE ?: synchronized(this) {
                val instance =
                    Room
                        .databaseBuilder(
                            context.applicationContext,
                            FriendsRoomDatabase::class.java,
                            "word_database",
                        ).build()
                INSTANCE = instance
                instance
            }
    }
}
