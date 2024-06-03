package com.pdm115gt3g2.pedidosapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm115gt3g2.pedidosapp.db.pedidos.PedidoDetalle

@Dao
interface PedidoDetalleDao{
    @Query("SELECT * FROM PedidoDetalle")
    fun getAll(): List<PedidoDetalle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pedidoDetalle: PedidoDetalle)

    @Delete
    fun deletePedidoDetalle(vararg pedidoDetalle: PedidoDetalle)
}