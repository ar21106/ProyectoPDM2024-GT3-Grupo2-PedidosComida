package com.pdm115gt3g2.pedidosapp.db.pedidos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DireccionEntrega(
    val nombreDireccion: String,
    val nombreCalle: String,
    val coloniaBarrio: String,
    val numCasa: String,
    val departamento: String,
    val municipio: String,
    val referencia: String,
    @PrimaryKey(autoGenerate = true) val idDireccion: Int = 0
)