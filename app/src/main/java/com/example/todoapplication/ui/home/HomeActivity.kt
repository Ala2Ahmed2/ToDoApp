package com.example.todoapplication.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.databinding.ActivityHomeBinding
import com.example.todoapplication.ui.addTask.AddTaskBottomSheet
import com.example.todoapplication.ui.settings.SettingsFragment
import com.example.todoapplication.ui.tasksList.TasksListFragment

class HomeActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
    }

    private fun setUpViews() {
        binding.bottomNavigation.setOnItemSelectedListener{ item ->
            if (item.itemId == R.id.navigation_tasks){
                showFragment(TasksListFragment())
            }else{
                showFragment(SettingsFragment())
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.selectedItemId = R.id.navigation_tasks
        binding.fabAddTask.setOnClickListener{
            showAddTaskBottomSheet()
        }
    }

    private fun showAddTaskBottomSheet() {
        val addTaskSheet = AddTaskBottomSheet()
        addTaskSheet.onTaskAddedListener = AddTaskBottomSheet.OnTaskAddedListener {
            supportFragmentManager.fragments.forEach{ fragment ->
                if (fragment is TasksListFragment && fragment.isAdded){
                    fragment.retreiveTasksList()
                }
            }
        }
        addTaskSheet.show(supportFragmentManager,null)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()

    }
}