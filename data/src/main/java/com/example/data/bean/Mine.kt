package com.example.data.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Mine(
    @PrimaryKey
    val uid: Long,
    @ColumnInfo(name = "user_name")
    val userName: String? = "",
    val email: String,
)
