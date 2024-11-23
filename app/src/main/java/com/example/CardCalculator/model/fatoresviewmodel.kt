package com.example.CardCalculator.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.CardCalculator.repository.fatoresrepository

class fatoresviewmodel(private val repository: fatoresrepository) : ViewModel() {

    private val _fatoresList = MutableLiveData<List<fatores>>()
    val fatoresList: LiveData<List<fatores>> get() = _fatoresList

    init {
        loadFatores()
    }

    fun savefatoresdata(valorinicial: Float, taxajuros: Float, aporte: Float, resultado: Float, tempo: Int) {
        val fator = fatores(valorinicial, taxajuros, aporte, tempo, resultado)
        repository.savefatores(fator)
        loadFatores()
    }

    fun deletefator(index: Int) {
        repository.deletefator(index)
        loadFatores()
    }

     fun loadFatores() {
        _fatoresList.value = repository.getfatores()
    }

}
