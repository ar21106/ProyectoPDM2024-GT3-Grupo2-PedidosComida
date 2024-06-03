package com.pdm115gt3g2.pedidosapp.ui.direccion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pdm115gt3g2.pedidosapp.db.PedidosAppDataBase
import com.pdm115gt3g2.pedidosapp.db.repositories.DireccionRepository
import com.pdm115gt3g2.pedidosapp.db.repositories.PedidoRepository

class DireccionViewModel(application: Application) : AndroidViewModel(application){
    private val _direccionRepository: DireccionRepository
    private val _pedidoRepository: PedidoRepository
    val direccionRepository: DireccionRepository get() = _direccionRepository
    val pedidoRepository: PedidoRepository get() = _pedidoRepository

    init{
        val db = PedidosAppDataBase.getDatabase(application)
        val direccionEntregaDao = db.DireccionEntregaDao()
        val pedidoDao = db.PedidoDao()
        _direccionRepository = DireccionRepository(direccionEntregaDao)
        _pedidoRepository = PedidoRepository(pedidoDao)
    }
}