package com.pdm115gt3g2.pedidosapp.db.relaciones

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pdm115gt3g2.pedidosapp.db.menus.Item
import com.pdm115gt3g2.pedidosapp.db.menus.Menu
import com.pdm115gt3g2.pedidosapp.db.menus.MenuDetalle

//Clase para guardar un menu con su listado de items
data class MenuConItems(
    @Embedded val menu: Menu,
    @Relation(
        entity = Item::class,
        parentColumn = "idMenu",
        entityColumn = "idItem",
        associateBy = Junction(MenuDetalle::class)
    )
    val items: List<ItemConTipoItem>
)