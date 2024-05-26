package com.pdm115gt3g2.pedidosapp.db.menus

import androidx.room.Entity

@Entity(primaryKeys = ["idMenu", "idItem"])
data class MenuDetalle(
    val idMenu: Int,
    val idItem: Int
)