package com.pdm115gt3g2.pedidosapp.db.repositories

import com.pdm115gt3g2.pedidosapp.db.dao.TipoItemDao
import com.pdm115gt3g2.pedidosapp.db.menus.TipoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TipoItemRepository(private var tipoItemDao: TipoItemDao){
    fun insertar(tipoItem: TipoItem){
        CoroutineScope(Dispatchers.IO).launch {
            tipoItemDao.insertAll(tipoItem)
        }
    }
}