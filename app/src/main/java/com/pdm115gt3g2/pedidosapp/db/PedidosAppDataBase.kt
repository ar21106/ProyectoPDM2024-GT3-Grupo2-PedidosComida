package com.pdm115gt3g2.pedidosapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.pdm115gt3g2.pedidosapp.db.dao.ItemDao
import com.pdm115gt3g2.pedidosapp.db.dao.MenuDao
import com.pdm115gt3g2.pedidosapp.db.dao.MenuDetalleDao
import com.pdm115gt3g2.pedidosapp.db.dao.TipoItemDao
import com.pdm115gt3g2.pedidosapp.db.menus.*
import java.util.Date

@Database(entities = [
    Item::class,
    Menu::class,
    MenuDetalle::class,
    TipoItem::class
                     ],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class PedidosAppDataBase : RoomDatabase(){
    abstract fun ItemDao(): ItemDao
    abstract fun MenuDao(): MenuDao
    abstract fun MenuDetalleDao(): MenuDetalleDao
    abstract fun TipoItemDao(): TipoItemDao

    //usado para llamar a la instancia de la base de datos solo una vez
    //siguiendo el "singleton pattern"
    companion object {
        @Volatile
        private var INSTANCE: PedidosAppDataBase? = null

        fun getDatabase(context: Context): PedidosAppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PedidosAppDataBase::class.java,
                    "pedidosAppDb"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}

// Para convertir Date a Long (Room no soporta Date sin estos conversores)
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}