package com.pdm115gt3g2.pedidosapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pdm115gt3g2.pedidosapp.db.pedidos.EstadoPedido

@Dao
interface EstadoPedidoDao{
    @Query("SELECT * FROM EstadoPedido")
    fun getAll(): List<EstadoPedido>

    @Insert
    fun insertAll(vararg estados: EstadoPedido)
}