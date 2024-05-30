package com.pdm115gt3g2.pedidosapp.ui.pedidos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pdm115gt3g2.pedidosapp.db.PedidosAppDataBase
import com.pdm115gt3g2.pedidosapp.db.relaciones.ItemConTipoItem
import com.pdm115gt3g2.pedidosapp.db.repositories.PedidoRepository

class PedidosViewModel(application: Application) : AndroidViewModel(application){
    //para acceder al repositorio
    private val pedidoRepository: PedidoRepository
    //para los valores nulos, cuando al iniciar la app la bd no tiene nada
    private val _pedidoItems = MutableLiveData<List<ItemConTipoItem>>()
    //para cuando la bd ya tenga data (el worker ha terminado de añadir la data)
    val pedidoItems: LiveData<List<ItemConTipoItem>> get() = _pedidoItems

    init {
        val db = PedidosAppDataBase.getDatabase(application)
        val pedidoDao = db.PedidoDao()
        pedidoRepository = PedidoRepository(pedidoDao)
        fetchMenuItems()
    }

    private fun fetchMenuItems() {
        pedidoRepository.buscarPorId(1).observeForever { pedido ->
            _pedidoItems.postValue(pedido?.items ?: emptyList())
        }
    }
}