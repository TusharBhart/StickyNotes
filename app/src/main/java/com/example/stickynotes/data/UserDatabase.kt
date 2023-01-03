package com.example.stickynotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class],version = 2,exportSchema = false)
abstract class UserDatabase:RoomDatabase() {

    abstract fun userDao():UserDao

    companion object{
        @Volatile
        private var INSTANCE : UserDatabase? = null

        val migration_1_2 :Migration =object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_table ADD COLUMN  title TEXT DEFAULT ''")
            }

        }

        fun getDatabase(context: Context):UserDatabase{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).addMigrations(migration_1_2).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}