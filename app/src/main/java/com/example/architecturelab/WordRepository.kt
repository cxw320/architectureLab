package com.example.architecturelab

import androidx.annotation.WorkerThread
import java.util.concurrent.Flow
import retrofit2.Callback

//declares the DAO as a private property in the constructor,
// pass in the DAO instead of the whole database because you only need access to the DAO

class WordRepository(private val wordDao: WordDao) {

    //Room executes all queries on a separate thread
    // Observed Flow will notify the observer when the data has changed
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()



    //Room runs suspend queries off the main thread by default
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word:Word){
        wordDao.insert(word)
    }


}