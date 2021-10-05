package com.example.todotypicodeexample.api

class TodoManager {

    private val todoService: TodoService
    private var retrofit = RetrofitInstance.providesRetrofit()

    init {
        todoService = retrofit.create(TodoService::class.java)
    }


    suspend fun getTodos() =
        todoService.getTodos()


}