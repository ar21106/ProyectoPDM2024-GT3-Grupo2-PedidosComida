package com.pdm115gt3g2.pedidosapp.db.repositories

import androidx.lifecycle.LiveData
import com.pdm115gt3g2.pedidosapp.db.dao.PedidoDao
import com.pdm115gt3g2.pedidosapp.db.relaciones.PedidoConItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PedidoRepository(private var pedidoDao: PedidoDao){

    val pedidoItems: LiveData<PedidoConItems> = pedidoDao.findById(1)

    fun buscarPorId(id: Int): LiveData<PedidoConItems> {
        return pedidoDao.findById(id)
    }

    fun actualizarEstado(id: Int, idEstado: Int){
        CoroutineScope(Dispatchers.IO).launch {
            pedidoDao.updateEstadoPedido(idEstado, id)
        }
    }
}