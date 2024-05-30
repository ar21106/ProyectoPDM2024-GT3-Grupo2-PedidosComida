package com.pdm115gt3g2.pedidosapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pdm115gt3g2.pedidosapp.db.pedidos.Pedido
import com.pdm115gt3g2.pedidosapp.db.relaciones.PedidoConItems

@Dao
interface PedidoDao{
    @Transaction
    @Query("SELECT * FROM Pedido WHERE idPedido = :idPedido")
    fun findById(idPedido: Int): LiveData<PedidoConItems>

    @Insert
    fun insertAll(vararg pedido: Pedido)
}