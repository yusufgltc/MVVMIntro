package com.edushare.mvvmintro.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edushare.mvvmintro.model.Note

class MainViewModel: ViewModel() {
    var list = MutableLiveData<ArrayList<Note>>()

    var newList = arrayListOf<Note>()

    fun add(note: Note){
        newList.add(note)
        list.value = newList
    }

    fun remove(note: Note){
        newList.remove(note)
        list.value = newList
    }
}