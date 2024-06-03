package com.pdm115gt3g2.pedidosapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm115gt3g2.pedidosapp.db.pedidos.DireccionEntrega

@Dao
interface DireccionEntregaDao{
    @Query("SELECT * FROM DireccionEntrega")
    fun getAll(): List<DireccionEntrega>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg direccionEntrega: DireccionEntrega)
}