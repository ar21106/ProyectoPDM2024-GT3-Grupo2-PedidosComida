package com.pdm115gt3g2.pedidosapp.db.repositories

import androidx.lifecycle.LiveData
import com.pdm115gt3g2.pedidosapp.db.dao.PedidoDao
import com.pdm115gt3g2.pedidosapp.db.relaciones.PedidoConItems

class PedidoRepository(private var pedidoDao: PedidoDao){

    fun buscarPorId(id: Int): LiveData<PedidoConItems> {
        return pedidoDao.findById(id)
    }
}