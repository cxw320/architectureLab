package com.example.architecturelab

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository): ViewModel() {

    //using LiveData and caching what allWords returns has several benefits:
    // we can put an observer on the data and only update UI when data changes
    // repository is completely separated from the Ui through the view model

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    // launching a new coroutine to insert the data in a non-blocking way

    // required in gradle:  implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha02"
    fun insert(word: Word) = viewModelScope.launch{
        repository.insert(word)
    }

    class WordViewModelFactory(private val repository: WordRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>):T {
            if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WordViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}