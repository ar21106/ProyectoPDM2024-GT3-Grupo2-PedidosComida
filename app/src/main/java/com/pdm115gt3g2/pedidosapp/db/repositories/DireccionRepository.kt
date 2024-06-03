package com.pdm115gt3g2.pedidosapp.db.repositories

import com.pdm115gt3g2.pedidosapp.db.dao.DireccionEntregaDao
import com.pdm115gt3g2.pedidosapp.db.pedidos.DireccionEntrega
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DireccionRepository(private var direccionDao: DireccionEntregaDao){

    fun insertar(direccion: DireccionEntrega){
        CoroutineScope(Dispatchers.IO).launch {
            direccionDao.insertAll(direccion)
        }
    }
}