package com.pdm115gt3g2.pedidosapp.db.pedidos

import androidx.room.Entity

@Entity(primaryKeys = ["idPedido", "idItem"])
data class PedidoDetalle(
    val idPedido: Int,
    val idItem: Int,
    val cantidadItem: Int
)