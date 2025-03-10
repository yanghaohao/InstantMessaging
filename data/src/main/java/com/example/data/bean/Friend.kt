package com.example.data.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_table")
data class Friend(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "request_id")
    val requestId: Long,
    @ColumnInfo(name = "addressee_id")
    val addresseeId: Long,
    val status: RequestStatus,
)

enum class RequestStatus(
    status: String,
) {
    WAITING("waiting"),
    REFUSE("refuse"),
    ACCEPTED("accepted"),
    EXPIRE("expire"),
}
