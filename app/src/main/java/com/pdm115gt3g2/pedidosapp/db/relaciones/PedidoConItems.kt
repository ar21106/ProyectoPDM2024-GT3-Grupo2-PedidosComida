package com.pdm115gt3g2.pedidosapp.db.relaciones

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pdm115gt3g2.pedidosapp.db.menus.Item
import com.pdm115gt3g2.pedidosapp.db.pedidos.PedidoDetalle

data class PedidoConItems(
    @Embedded val pedido: PedidoConEstadoPedido,
    @Relation(
        entity = Item::class,
        parentColumn = "idPedido",
        entityColumn = "idItem",
        associateBy = Junction(PedidoDetalle::class)
    )
    val items: List<ItemConCantidad>
)