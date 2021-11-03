package com.edushare.mvvmintro.view

import android.annotation.SuppressLint
import com.edushare.mvvmintro.adapter.NoteAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edushare.mvvmintro.R
import com.edushare.mvvmintro.model.Note
import com.edushare.mvvmintro.viewmodel.MainViewModel
import com.edushare.mvvmintro.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var addButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainRecyclerView = findViewById(R.id.recycler_view)
        val application = requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory)[MainViewModel::class.java]
        addButton = findViewById(R.id.add_button)

        addButton.setOnClickListener {
            addData()
        }

        initialiseAdapter()
    }
    private fun initialiseAdapter(){
        mainRecyclerView.layoutManager = viewManager
        observeData()
    }

    private fun observeData(){
        viewModel.list.observe(this, Observer{
            Log.i("data",it.toString())
            mainRecyclerView.adapter= NoteAdapter(viewModel, it, this)
        })
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun addData(){
        val editTextNote = findViewById<EditText>(R.id.text_title)
        val title=editTextNote.text.toString()
        if(title.isBlank()){
            Toast.makeText(this,"Enter value!",Toast.LENGTH_LONG).show()
        }else{
            val blog= Note(title)
            viewModel.add(blog)
            editTextNote.text.clear()
            mainRecyclerView.adapter?.notifyDataSetChanged()
        }

    }
}