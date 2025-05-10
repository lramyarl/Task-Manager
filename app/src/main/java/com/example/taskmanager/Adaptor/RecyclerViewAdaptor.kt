package com.example.taskmanager.Adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.MVP.Ext.OnBind
import com.example.taskmanager.databinding.RecyclerItemBinding

import info.alirezaahmadi.taskapp.db.model.TaskEntity

class RecyclerViewAdaptor(
    private val task: ArrayList<TaskEntity>,
    private val onBind: OnBind
) : RecyclerView.Adapter<RecyclerViewAdaptor.TaskViewHolder>() {

    inner class TaskViewHolder(
        private val binding: RecyclerItemBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: TaskEntity) {

            binding.txtTitle.text = data.title

            binding.checkBox.isChecked = data.state
            binding.checkBox.setOnClickListener {

                if(data.state)
                    onBind.editData(TaskEntity(data.id , data.title, false))
                else
                    onBind.editData(TaskEntity(data.id , data.title, true))
            }
            binding.imgDelete.setOnClickListener {
                onBind.deleteData(data)
                
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int = task.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.setData(task[position])
    }

    fun updateData(newList:ArrayList<TaskEntity>){

        val diffUtilsCallback = RecyclerDiffUtills(task , newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilsCallback)

        task.clear()
        task.addAll(newList)

        diffResult.dispatchUpdatesTo(this)

    }
}