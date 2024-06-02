package com.pdm115gt3g2.pedidosapp.db.repositories

import com.pdm115gt3g2.pedidosapp.db.dao.PedidoDetalleDao
import com.pdm115gt3g2.pedidosapp.db.pedidos.PedidoDetalle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PedidoDetalleRepository(private var pedidoDetalleDao: PedidoDetalleDao){

    fun insertar(pedidoDetalle: PedidoDetalle){
        CoroutineScope(Dispatchers.IO).launch {
            pedidoDetalleDao.insertAll(pedidoDetalle)
        }
    }

    fun eliminar(pedidoDetalle: PedidoDetalle){
        CoroutineScope(Dispatchers.IO).launch {
            pedidoDetalleDao.deletePedidoDetalle(pedidoDetalle)
        }
    }
}