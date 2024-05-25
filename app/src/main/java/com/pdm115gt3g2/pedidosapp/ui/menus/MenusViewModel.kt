package com.pdm115gt3g2.pedidosapp.ui.menus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenusViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "aqui se verán todos los demás menus"
    }
    val text: LiveData<String> = _text
}