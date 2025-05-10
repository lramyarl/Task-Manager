package com.example.taskmanager.db.Dao

import androidx.room.*
import info.alirezaahmadi.taskapp.db.DBHandler
import info.alirezaahmadi.taskapp.db.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Insert
    fun insertTask(vararg task: TaskEntity)

    @get:Query("SELECT * FROM ${DBHandler.TASK_TABLE}")
    val getTasks: Flow<List<TaskEntity>>

    @Query("SELECT * FROM ${DBHandler.TASK_TABLE} WHERE state = :type")
    fun getTasksByColumn(type: Boolean): Flow<List<TaskEntity>>

    @Update
    fun updateTasks(vararg tasks: TaskEntity): Int

    @Delete
    fun deleteTasks(vararg tasks: TaskEntity): Int

    @Query("DELETE FROM ${DBHandler.TASK_TABLE}")
    fun deleteAllTasks()

}