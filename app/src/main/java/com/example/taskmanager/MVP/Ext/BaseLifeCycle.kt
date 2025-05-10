package com.example.taskmanager.MVP.Ext

interface BaseLifeCycle {
    fun onCreate()
    fun onDestroy(){
    }
    fun onStop(){}
}