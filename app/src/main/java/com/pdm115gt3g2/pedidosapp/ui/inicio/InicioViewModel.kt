package com.pdm115gt3g2.pedidosapp.ui.inicio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InicioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "aqui se verá el menú principal"
    }
    val text: LiveData<String> = _text
}