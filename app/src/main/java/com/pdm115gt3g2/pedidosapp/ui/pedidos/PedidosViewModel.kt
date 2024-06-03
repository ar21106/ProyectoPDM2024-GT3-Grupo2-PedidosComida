package com.pdm115gt3g2.pedidosapp.ui.pedidos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pdm115gt3g2.pedidosapp.db.PedidosAppDataBase
import com.pdm115gt3g2.pedidosapp.db.relaciones.ItemConCantidad
import com.pdm115gt3g2.pedidosapp.db.relaciones.PedidoConItems
import com.pdm115gt3g2.pedidosapp.db.repositories.PedidoRepository

class PedidosViewModel(application: Application) : AndroidViewModel(application){

    private val pedidoRepository: PedidoRepository
    private val _pedidoItems = MutableLiveData<List<ItemConCantidad>>()
    val pedidoItems: LiveData<List<ItemConCantidad>> get() = _pedidoItems
    val pedidoConItems: LiveData<PedidoConItems>

    init {
        val db = PedidosAppDataBase.getDatabase(application)
        val pedidoDao = db.PedidoDao()
        pedidoRepository = PedidoRepository(pedidoDao)
        pedidoConItems = pedidoRepository.pedidoItems
        fetchPedidoItems()
    }

    private fun fetchPedidoItems() {
        pedidoRepository.buscarPorId(1).observeForever { pedido ->
            _pedidoItems.postValue(pedido?.items ?: emptyList())
        }
    }
}