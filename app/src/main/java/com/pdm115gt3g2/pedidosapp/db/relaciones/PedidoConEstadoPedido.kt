package com.pdm115gt3g2.pedidosapp.db.relaciones

import androidx.room.Embedded
import androidx.room.Relation
import com.pdm115gt3g2.pedidosapp.db.pedidos.EstadoPedido
import com.pdm115gt3g2.pedidosapp.db.pedidos.Pedido

data class PedidoConEstadoPedido(
    @Embedded val pedido: Pedido,
    @Relation(
        parentColumn = "idEstado",
        entityColumn = "idEstado"
    )
    val estadoPedido: EstadoPedido
)