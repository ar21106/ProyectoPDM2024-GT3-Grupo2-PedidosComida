package com.pdm115gt3g2.pedidosapp.db.repositories

import com.pdm115gt3g2.pedidosapp.db.dao.MenuDetalleDao
import com.pdm115gt3g2.pedidosapp.db.menus.MenuDetalle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuDetalleRepository(private var menuDetalleDao: MenuDetalleDao) {

    fun insertar(menuDetalle: MenuDetalle) {
        CoroutineScope(Dispatchers.IO).launch {
            menuDetalleDao.insertMenuDetalle(menuDetalle)
        }
    }
}