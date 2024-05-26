package com.pdm115gt3g2.pedidosapp.db.repositories

import com.pdm115gt3g2.pedidosapp.db.dao.ItemDao
import com.pdm115gt3g2.pedidosapp.db.menus.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemRepositorio(private var itemDao: ItemDao){

    fun insertar(item: Item){
        CoroutineScope(Dispatchers.IO).launch {
            itemDao.insertItem(item)
        }
    }
}