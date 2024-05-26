package com.pdm115gt3g2.pedidosapp.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.pdm115gt3g2.pedidosapp.db.relaciones.MenuConItems

@Dao
interface MenuDao{
    @Transaction
    @Query("SELECT * FROM Menu")
    fun getMenuConItems(): List<MenuConItems>
}