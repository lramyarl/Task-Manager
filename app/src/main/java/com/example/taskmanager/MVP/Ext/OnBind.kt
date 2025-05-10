package com.example.taskmanager.MVP.Ext

import info.alirezaahmadi.taskapp.db.model.TaskEntity

interface OnBind {
    fun saveData(task : TaskEntity){

    }
    fun editData(task : TaskEntity){}
    fun deleteData(task : TaskEntity){}

    fun getData(task: List<TaskEntity>){}

    fun requestData(state:Boolean){

    }
}