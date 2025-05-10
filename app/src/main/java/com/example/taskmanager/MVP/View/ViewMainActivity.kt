package com.example.taskmanager.MVP.View

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.Adaptor.RecyclerViewAdaptor
import com.example.taskmanager.MVP.Ext.OnBind
import com.example.taskmanager.databinding.ActivityMainBinding
import com.example.taskmanager.databinding.CustomDialogBinding
import info.alirezaahmadi.taskapp.db.model.TaskEntity

class ViewMainActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {
    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

    fun showDialog(onBind : OnBind){

        binding.fab.setOnClickListener {

            val view = CustomDialogBinding.inflate(LayoutInflater.from(context))

            val dialog = Dialog(context)

            dialog.setContentView(view.root)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            view.btnCancel.setOnClickListener { dialog.dismiss() }

            view.btnSave.setOnClickListener {

            val text = view.edtTask.text.toString()

                if(text.isNotEmpty()){

                    onBind.saveData(TaskEntity(title = text , state = false))
                    Toast.makeText(context, "وظیفه با موفقیت بسته شد", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                }
                else{
                    Toast.makeText(context,"لطفا یک وظیفه وارد کنید", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private lateinit var adapter : RecyclerViewAdaptor
    fun initRecycler(tasks:ArrayList<TaskEntity>, onBind: OnBind){
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context , RecyclerView.VERTICAL , false)
        adapter = RecyclerViewAdaptor(tasks, onBind)
        binding.recyclerView.adapter = adapter

    }

    fun showTask(task : List<TaskEntity>){
        val data = arrayListOf<TaskEntity>()
        task.forEach{data.add(it)}

        adapter.updateData(data)

    }

    fun setData(onBind:OnBind){
        onBind.requestData(false)

        binding.rbTrue.setOnClickListener{
            onBind.requestData(true)
        }
        binding.rbFalse.setOnClickListener{
            onBind.requestData(false)
        }


    }
}