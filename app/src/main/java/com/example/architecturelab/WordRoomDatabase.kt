package com.example.architecturelab

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//export schema is true if you want export and check current schema into your version control version
//each entity correspond to a table that will be create in the database
@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class WordRoomDatabase: RoomDatabase() {

    //data access object
    abstract fun wordDao(): WordDao


    companion object{
        //Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        //first checks if the instance is not null, if it is null then it creates db for first time
        fun getDatabase(context: Context): WordRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                //return instance
                instance
            }
        }


    }
}