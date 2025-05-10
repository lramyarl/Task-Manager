package com.example.taskmanager.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskmanager.MVP.Model.ModelMainActivity
import com.example.taskmanager.MVP.Persenter.PresenterMainActivity
import com.example.taskmanager.MVP.View.ViewMainActivity
import com.example.taskmanager.R

class MainActivity : AppCompatActivity() {
    private lateinit var presenter : PresenterMainActivity
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val view = ViewMainActivity(this)

        setContentView(view.binding.root)
        presenter = PresenterMainActivity(ModelMainActivity(this),view)
        presenter.onCreate()




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}