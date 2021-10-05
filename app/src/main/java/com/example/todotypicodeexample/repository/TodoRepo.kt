package com.example.todotypicodeexample.repository

import android.util.Log
import com.example.todotypicodeexample.api.TodoManager
import com.example.todotypicodeexample.utils.Resource
import kotlinx.coroutines.flow.flow

class TodoRepo(private val todoManager: TodoManager) {

    fun getTodos() = flow {

        val resource = try {
            val response = todoManager.getTodos()
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.errorBody().toString())
            }
        } catch (ex: Exception) {
            Log.d(TAG, ex.toString())
            Resource.Error(ex.toString())
        }
        emit(resource)
    }




    companion object {
        private val TAG = TodoRepo::class.java.name
    }
}