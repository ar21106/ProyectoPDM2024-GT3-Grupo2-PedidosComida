package com.pdm115gt3g2.pedidosapp.ui.inicio

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pdm115gt3g2.pedidosapp.db.PedidosAppDataBase
import com.pdm115gt3g2.pedidosapp.db.relaciones.ItemConTipoItem
import com.pdm115gt3g2.pedidosapp.db.repositories.MenuRepository

class InicioViewModel(application: Application) : AndroidViewModel(application){

    private val menuRepository: MenuRepository
    private val _menuItems = MutableLiveData<List<ItemConTipoItem>>()
    //getter para usarlo en el fragment
    val menuItems: LiveData<List<ItemConTipoItem>> get() = _menuItems

    //id del menu que se mostrará
    private val _id = MutableLiveData<Int>()

    init {
        val db = PedidosAppDataBase.getDatabase(application)
        val menuDao = db.MenuDao()
        menuRepository = MenuRepository(menuDao)
        _id.observeForever { id ->
            fetchMenuItems(id)
        }
    }

    private fun fetchMenuItems(id: Int) {
        menuRepository.buscarPorId(id).observeForever { menu ->
            _menuItems.postValue(menu?.items ?: emptyList())
        }
    }

    fun setId(id: Int) {
        _id.value = id
    }

}