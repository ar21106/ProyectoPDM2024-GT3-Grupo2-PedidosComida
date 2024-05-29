package com.pdm115gt3g2.pedidosapp.ui.menus

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pdm115gt3g2.pedidosapp.db.PedidosAppDataBase
import com.pdm115gt3g2.pedidosapp.db.menus.Menu
import com.pdm115gt3g2.pedidosapp.db.repositories.MenuRepository

class MenusViewModel(application: Application) : AndroidViewModel(application) {

    //para acceder al repositorio
    private val menuRepository: MenuRepository
    //para los valores nulos, cuando al iniciar la app la bd no tiene nada
    private val _menus = MutableLiveData<List<Menu>>()
    //para cuando la bd ya tenga data (el worker ha terminado de añadir la data)
    val menus: LiveData<List<Menu>> get() = _menus

    init {
        val db = PedidosAppDataBase.getDatabase(application)
        val menuDao = db.MenuDao()
        menuRepository = MenuRepository(menuDao)
        fetchMenus()
    }

    private fun fetchMenus() {
        menuRepository.extraerMenus().observeForever { menus ->
            _menus.postValue(menus ?: emptyList())
        }
    }

}