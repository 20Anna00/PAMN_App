package com.example.pillee.jetpackcompnavigation.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(NotificationDB::class), version = 1, exportSchema = false)
abstract class NotificationRoomDatabase : RoomDatabase() {

    abstract fun accessDao(): NotificationDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotificationRoomDatabase? = null

        fun getDatabase(context: Context): NotificationRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, NotificationRoomDatabase::class.java,
                    "notification_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}