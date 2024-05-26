package com.pdm115gt3g2.pedidosapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pdm115gt3g2.pedidosapp.db.menus.TipoItem

@Dao
interface TipoItemDao{
    @Query("SELECT * FROM TipoItem")
    fun getAll(): List<TipoItem>

    @Insert
    fun insertAll(vararg tipoItem: TipoItem)
}