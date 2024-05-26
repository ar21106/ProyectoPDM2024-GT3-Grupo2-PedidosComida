package com.pdm115gt3g2.pedidosapp.db.menus

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Menu(
    val nombreMenu: String,
    val descripcionMenu: String,
    val fechaCreado: Date,
    val disponible: Int,
    @PrimaryKey(autoGenerate = true) val idMenu: Int = 0
)