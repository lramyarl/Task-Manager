package com.example.taskmanager.MVP.Persenter


import com.example.taskmanager.MVP.Ext.BaseLifeCycle
import com.example.taskmanager.MVP.Ext.OnBind
import com.example.taskmanager.MVP.Model.ModelMainActivity
import com.example.taskmanager.MVP.View.ViewMainActivity
import info.alirezaahmadi.taskapp.db.model.TaskEntity

class PresenterMainActivity(
    private val model: ModelMainActivity,
    private val view: ViewMainActivity
) : BaseLifeCycle {
    override fun onCreate() {
        addNewTask()
        setDataInitRecycler()
        dataHandler()
    }

    fun addNewTask() {
        view.showDialog(
            object : OnBind {
                override fun saveData(task: TaskEntity) {
                 model.setData(task)
                }

            }
        )
    }

    fun setDataInitRecycler() {
        view.initRecycler(
            arrayListOf(),
            object : OnBind {
                override fun editData(task: TaskEntity) {
                    model.editData(task)
                }

                override fun deleteData(task: TaskEntity) {
                    model.deleteData(task)
                }

            }
        )
    }

    override fun onDestroy() {
        model.closeDataBase()
    }


    private fun dataHandler() {
        view.setData(
            object : OnBind {
                override fun requestData(state: Boolean) {
                    model.getTask(
                        state,
                        object : OnBind {
                            override fun getData(task: List<TaskEntity>) {
                                view.showTask(task)
                            }
                        }
                    )
                }
            }
        )
    }
}