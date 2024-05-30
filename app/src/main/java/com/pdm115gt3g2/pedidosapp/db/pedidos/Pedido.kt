package com.pdm115gt3g2.pedidosapp.db.pedidos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Pedido(
    val idDireccion: Int,
    val idEstado: Int,
    val propina: Double,
    val fechaHoraPedido: Date,
    val totalPagar: Double,
    @PrimaryKey(autoGenerate = true) val idPedido: Int = 0
)