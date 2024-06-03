package com.pdm115gt3g2.pedidosapp.ui.pedidos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pdm115gt3g2.pedidosapp.db.PedidosAppDataBase
import com.pdm115gt3g2.pedidosapp.db.repositories.PedidoDetalleRepository

class AgregarItemViewModel(application: Application) : AndroidViewModel(application){

    //para acceder al repositorio
    private val _pedidoDetalleRepository: PedidoDetalleRepository
    val pedidoDetalleRepository: PedidoDetalleRepository get() = _pedidoDetalleRepository

    init {
        val db = PedidosAppDataBase.getDatabase(application)
        val pedidoDetalleDao = db.PedidoDetalleDao()
        _pedidoDetalleRepository = PedidoDetalleRepository(pedidoDetalleDao)
    }
}