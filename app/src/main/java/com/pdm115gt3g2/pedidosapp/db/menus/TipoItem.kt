package com.pdm115gt3g2.pedidosapp.db.menus

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TipoItem(
    val nombreTipo: String,
    @PrimaryKey(autoGenerate = true) val idTipo: Int = 0
)