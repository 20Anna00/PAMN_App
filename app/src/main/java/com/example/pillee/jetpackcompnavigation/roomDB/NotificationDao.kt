package com.example.pillee.jetpackcompnavigation.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotificationDao {
    @Query("SELECT * from notification_table ORDER BY dateMillis ASC")
    fun getAlphabetizedNotifications(): LiveData<List<NotificationDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg notification: NotificationDB)
}