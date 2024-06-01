package com.pdm115gt3g2.pedidosapp.db.relaciones

import androidx.room.Embedded
import androidx.room.Relation
import com.pdm115gt3g2.pedidosapp.db.menus.Item
import com.pdm115gt3g2.pedidosapp.db.menus.TipoItem


data class ItemConCantidad(
    @Embedded val item: Item,
    @Relation(
        parentColumn = "idTipo",
        entityColumn = "idTipo"
    )
    val tipo: TipoItem
    // TODO hacer funcionar la cantidad de la relacion pedido con items del pedido
    //val cantidad: Int
)