package com.pdm115gt3g2.pedidosapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pdm115gt3g2.pedidosapp.db.menus.Menu
import com.pdm115gt3g2.pedidosapp.db.relaciones.MenuConItems

@Dao
interface MenuDao{
    @Transaction
    @Query("SELECT * FROM Menu")
    fun getMenuConItems(): List<MenuConItems>

    @Transaction
    @Query("SELECT * FROM Menu Where idMenu = :idMenu")
    fun findById(idMenu: Int): LiveData<MenuConItems>

    @Insert
    fun insertMenu(vararg menu: Menu)
}