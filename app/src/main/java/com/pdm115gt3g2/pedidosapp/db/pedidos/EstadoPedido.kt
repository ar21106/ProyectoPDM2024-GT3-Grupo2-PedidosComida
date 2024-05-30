package com.pdm115gt3g2.pedidosapp.db.pedidos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EstadoPedido(
    val nombreEstado: String,
    @PrimaryKey(autoGenerate = true) val idEstado: Int = 0
)