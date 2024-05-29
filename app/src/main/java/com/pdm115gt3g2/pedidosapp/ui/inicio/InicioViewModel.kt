package com.pdm115gt3g2.pedidosapp.ui.inicio

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pdm115gt3g2.pedidosapp.db.PedidosAppDataBase
import com.pdm115gt3g2.pedidosapp.db.relaciones.ItemConTipoItem
import com.pdm115gt3g2.pedidosapp.db.repositories.MenuRepository

class InicioViewModel(application: Application) : AndroidViewModel(application){

    //para acceder al repositorio
    private val menuRepository: MenuRepository
    //para los valores nulos, cuando al iniciar la app la bd no tiene nada
    private val _menuItems = MutableLiveData<List<ItemConTipoItem>>()
    //para cuando la bd ya tenga data (el worker ha terminado de añadir la data)
    val menuItems: LiveData<List<ItemConTipoItem>> get() = _menuItems

    init {
        val db = PedidosAppDataBase.getDatabase(application)
        val menuDao = db.MenuDao()
        menuRepository = MenuRepository(menuDao)
        fetchMenuItems()
    }

    private fun fetchMenuItems() {
        menuRepository.buscarPorId(1).observeForever { menu ->
            _menuItems.postValue(menu?.items ?: emptyList())
        }
    }

}