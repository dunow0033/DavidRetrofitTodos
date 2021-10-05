package com.example.todotypicodeexample.ui.viewmodel

import androidx.lifecycle.*
import com.example.todotypicodeexample.model.Todo
import com.example.todotypicodeexample.repository.TodoRepo
import com.example.todotypicodeexample.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class TodoViewModel(val repo: TodoRepo) : ViewModel() {


    private var _todos: MutableLiveData<Resource<List<Todo>>> = MutableLiveData()
    val todos: LiveData<Resource<List<Todo>>> get() = _todos

    init {
        getTodos()
    }


    private fun getTodos() {

        viewModelScope.launch {
            repo.getTodos().collect {
                _todos.postValue(it)
            }
        }

    }

}

class TodoViewModelFactory(
    private val todoRepo: TodoRepo):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoViewModel(todoRepo) as T
    }


}




