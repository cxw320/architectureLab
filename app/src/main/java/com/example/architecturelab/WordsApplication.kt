package com.example.architecturelab

import android.app.Application

class WordsApplication: Application() {

    // Using by lazing so the database and the repository are only created when they're needed
    // rather than when the application starts

    val database by lazy { WordRoomDatabase.getDatabase(this)}
    val repository by lazy { WordRepository(database.wordDao()) }


}