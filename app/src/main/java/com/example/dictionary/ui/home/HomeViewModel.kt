package com.example.dictionary.ui.home

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.data.DatabaseAccess
import com.example.dictionary.models.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(private val databaseAccess: DatabaseAccess) : ViewModel() {

    companion object{
        val TAG = "HomeViewModel"
    }

    private val _words: MutableLiveData<List<Word>> = MutableLiveData()
    val words: LiveData<List<Word>>
        get() = _words

    private val _isLoading: MutableLiveData<Int> = MutableLiveData()
    val isLoading: LiveData<Int>
        get() = _isLoading

    private val scope = CoroutineScope(Dispatchers.Main)
    private var currentPos = 0

    init {
        loadData()
    }

    fun loadData() = scope.launch {
        _isLoading.value = View.VISIBLE

        val task = async(Dispatchers.Default) {
            try {
                Result(databaseAccess.getAllData(currentPos), null)
            }catch (e: Throwable){
                Result(null, e)
            }

        }
        val result = task.await()

        if (result.success != null){
            _words.value = result.success
        }else if (result.error != null){
            Log.e(TAG, result.error.toString())
        }
        currentPos += 50
        _isLoading.value = View.GONE
    }

    fun findWords(word: String) = scope.launch {
        _isLoading.value = View.VISIBLE

        val task = async(Dispatchers.Default) {
            try {
                Result(databaseAccess.findWords(word), null)
            }catch (e: Throwable){
                Result(null, e)
            }

        }
        val result = task.await()

        if (result.success != null){
            _words.value = result.success
        }else if (result.error != null){
            Log.e(TAG, result.error.toString())
        }
        _isLoading.value = View.GONE
    }

    fun selectWord(id: Int) : Word {
        val result = databaseAccess.getWord(id)
        return result
    }

    class Result<out T>(val success: T? = null, val error: Throwable? = null)

}