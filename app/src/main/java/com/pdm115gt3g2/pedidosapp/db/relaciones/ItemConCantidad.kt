package com.pdm115gt3g2.pedidosapp.db.relaciones

import androidx.room.Embedded
import androidx.room.Relation
import com.pdm115gt3g2.pedidosapp.db.menus.Item
import com.pdm115gt3g2.pedidosapp.db.menus.TipoItem
import com.pdm115gt3g2.pedidosapp.db.pedidos.PedidoDetalle


data class ItemConCantidad(
    @Embedded val item: Item,
    @Relation(
        parentColumn = "idTipo",
        entityColumn = "idTipo"
    )
    val tipo: TipoItem,
    @Relation(
        parentColumn = "idItem",
        entityColumn = "idItem"
    )
    val pedidoDetalle: PedidoDetalle
)