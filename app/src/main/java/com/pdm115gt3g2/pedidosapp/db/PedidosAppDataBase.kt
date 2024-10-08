package com.pdm115gt3g2.pedidosapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.pdm115gt3g2.pedidosapp.db.dao.DireccionEntregaDao
import com.pdm115gt3g2.pedidosapp.db.dao.EstadoPedidoDao
import com.pdm115gt3g2.pedidosapp.db.dao.ItemDao
import com.pdm115gt3g2.pedidosapp.db.dao.MenuDao
import com.pdm115gt3g2.pedidosapp.db.dao.MenuDetalleDao
import com.pdm115gt3g2.pedidosapp.db.dao.PedidoDao
import com.pdm115gt3g2.pedidosapp.db.dao.PedidoDetalleDao
import com.pdm115gt3g2.pedidosapp.db.dao.TipoItemDao
import com.pdm115gt3g2.pedidosapp.db.menus.Item
import com.pdm115gt3g2.pedidosapp.db.menus.Menu
import com.pdm115gt3g2.pedidosapp.db.menus.MenuDetalle
import com.pdm115gt3g2.pedidosapp.db.menus.TipoItem
import com.pdm115gt3g2.pedidosapp.db.pedidos.DireccionEntrega
import com.pdm115gt3g2.pedidosapp.db.pedidos.EstadoPedido
import com.pdm115gt3g2.pedidosapp.db.pedidos.Pedido
import com.pdm115gt3g2.pedidosapp.db.pedidos.PedidoDetalle
import java.util.Date

@Database(entities = [
    Item::class,
    Menu::class,
    MenuDetalle::class,
    TipoItem::class,
    Pedido::class,
    PedidoDetalle::class,
    EstadoPedido::class,
    DireccionEntrega::class

                     ],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class PedidosAppDataBase : RoomDatabase(){
    abstract fun ItemDao(): ItemDao
    abstract fun MenuDao(): MenuDao
    abstract fun MenuDetalleDao(): MenuDetalleDao
    abstract fun TipoItemDao(): TipoItemDao
    abstract fun PedidoDao(): PedidoDao
    abstract fun PedidoDetalleDao(): PedidoDetalleDao
    abstract fun EstadoPedidoDao(): EstadoPedidoDao
    abstract fun DireccionEntregaDao(): DireccionEntregaDao

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