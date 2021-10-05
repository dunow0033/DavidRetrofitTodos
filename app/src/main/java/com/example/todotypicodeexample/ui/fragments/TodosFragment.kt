package com.example.todotypicodeexample.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todotypicodeexample.R
import com.example.todotypicodeexample.api.TodoManager
import com.example.todotypicodeexample.databinding.FragmentTodosBinding
import com.example.todotypicodeexample.databinding.ItemTodoBinding
import com.example.todotypicodeexample.repository.TodoRepo
import com.example.todotypicodeexample.ui.TodoAdapter
import com.example.todotypicodeexample.ui.viewmodel.TodoViewModel
import com.example.todotypicodeexample.ui.viewmodel.TodoViewModelFactory


class TodosFragment : Fragment() {

    private var _binding: FragmentTodosBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TodoViewModel
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = (activity as MainActivity).viewModel

        viewModel = ViewModelProvider(
            this,
            TodoViewModelFactory(TodoRepo(TodoManager()))
        ).get(TodoViewModel::class.java)

        setupRecyclerView()


        viewModel.todos.observe(viewLifecycleOwner, Observer {
            todoAdapter.differ.submitList(it.data)
        })
    }

    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(requireContext())

    }


}