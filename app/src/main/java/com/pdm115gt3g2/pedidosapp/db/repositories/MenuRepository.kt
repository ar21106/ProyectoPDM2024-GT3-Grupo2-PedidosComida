package com.pdm115gt3g2.pedidosapp.db.repositories

import com.pdm115gt3g2.pedidosapp.db.dao.MenuDao
import com.pdm115gt3g2.pedidosapp.db.menus.Menu
import com.pdm115gt3g2.pedidosapp.db.relaciones.MenuConItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuRepository(private var menuDao: MenuDao) {

    fun insertar(menu: Menu) {
        CoroutineScope(Dispatchers.IO).launch {
            menuDao.insertMenu(menu)
        }
    }

    fun buscarPorId(id: Int): MenuConItems {
        return menuDao.findById(id)
    }
}