package com.example.pillee.jetpackcompnavigation.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.Timestamp

data class Notification (
    val userId: String,
    val dateTime: Long,
    val pill: String,

){
    constructor(): this("", 0, "")
}

@Entity(tableName = "notification_table")
data class NotificationDB(
    @PrimaryKey val dateMillis: Long,
    val pill: String,
    val userId: String
)

fun NotificationDB.toNotification(): Notification {
    return Notification(userId, dateMillis, pill)
}

fun Notification.toNotificationDB(): NotificationDB {
    return NotificationDB(dateTime, pill, userId)
}