package com.example.dictionary.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.models.Word

class DetailViewModel : ViewModel() {

    private val _word: MutableLiveData<Word> = MutableLiveData()
    val word: LiveData<Word>
        get() = _word

    fun passWord(word: Word){
        _word.value = word
    }
}