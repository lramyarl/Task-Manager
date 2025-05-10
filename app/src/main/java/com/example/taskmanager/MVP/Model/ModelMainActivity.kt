package com.example.taskmanager.MVP.Model

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.taskmanager.MVP.Ext.OnBind
import info.alirezaahmadi.taskapp.db.DBHandler
import info.alirezaahmadi.taskapp.db.model.TaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModelMainActivity(private val activity: AppCompatActivity) {
    private val db = DBHandler.getDatabase(activity)
     fun setData(taskEntity: TaskEntity){
         activity.lifecycleScope.launch {
             withContext(Dispatchers.IO){
                 db.taskDao().insertTask(taskEntity)

             }
         }
     }
    fun editData(taskEntity: TaskEntity){
         activity.lifecycleScope.launch {
             withContext(Dispatchers.IO){
                 db.taskDao().updateTasks(taskEntity)

             }
         }
     }
    fun deleteData(taskEntity: TaskEntity){
        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                db.taskDao().deleteTasks(taskEntity)

            }
        }
    }
    fun getTask(state:Boolean,onBind :OnBind){
        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val task = db.taskDao().getTasksByColumn(state)
                withContext(Dispatchers.Main){
                    task.collect{
                        onBind.getData(it)
                    }
                }
            }
        }

    }

    fun closeDataBase(){
        db.close()
    }


}