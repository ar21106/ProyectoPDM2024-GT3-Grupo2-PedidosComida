package com.pdm115gt3g2.pedidosapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pdm115gt3g2.pedidosapp.db.menus.Item

@Dao
interface ItemDao{
    @Query("SELECT * FROM Item")
    fun getAll(): List<Item>

    @Insert
    fun insertItem(vararg item: Item)
}