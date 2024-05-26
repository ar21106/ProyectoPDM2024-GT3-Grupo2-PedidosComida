package com.pdm115gt3g2.pedidosapp.db.menus

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    val idTipo: Int,
    val nombreItem: String,
    val descripcionItem: String,
    val precioItem: Double,
    @PrimaryKey(autoGenerate = true) val idItem: Int = 0
)