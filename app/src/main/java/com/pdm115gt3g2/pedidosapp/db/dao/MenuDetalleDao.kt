package com.pdm115gt3g2.pedidosapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pdm115gt3g2.pedidosapp.db.menus.MenuDetalle

@Dao
interface MenuDetalleDao{
    @Query("SELECT * FROM MenuDetalle")
    fun getAll(): List<MenuDetalle>

    @Insert
    fun insertMenuDetalle(vararg menuDetalle: MenuDetalle)

}