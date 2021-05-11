package com.repos.repoassignment.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.repos.repoassignment.utils.App

@Database(entities = [NewsListEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun newsListDAO(): NewsListDAO

    companion object {

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(): LocalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    App.instance.context, LocalDatabase::class.java, "local_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

    }
}