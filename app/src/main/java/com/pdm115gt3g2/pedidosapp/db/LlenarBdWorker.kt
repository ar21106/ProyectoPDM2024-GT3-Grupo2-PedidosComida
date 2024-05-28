package com.pdm115gt3g2.pedidosapp.db

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.pdm115gt3g2.pedidosapp.db.dao.ItemDao
import com.pdm115gt3g2.pedidosapp.db.dao.MenuDao
import com.pdm115gt3g2.pedidosapp.db.dao.MenuDetalleDao
import com.pdm115gt3g2.pedidosapp.db.dao.TipoItemDao
import com.pdm115gt3g2.pedidosapp.db.menus.Item
import com.pdm115gt3g2.pedidosapp.db.menus.Menu
import com.pdm115gt3g2.pedidosapp.db.menus.MenuDetalle
import com.pdm115gt3g2.pedidosapp.db.menus.TipoItem
import java.util.Date


class LlenarBdWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val db = PedidosAppDataBase.getDatabase(applicationContext)
        populateDatabase(db.ItemDao(), db.MenuDao(), db.MenuDetalleDao(), db.TipoItemDao())
        return Result.success()
    }

    private suspend fun populateDatabase(
        itemDao: ItemDao,
        menuDao: MenuDao,
        menuDetalleDao: MenuDetalleDao,
        tipoItemDao: TipoItemDao

    ) {
        // *** INSERTANDO TIPO DE ITEMS ***
        val tipo1 = TipoItem(nombreTipo = "hamburgesas")
        val tipo2 = TipoItem(nombreTipo = "pizzas")
        val tipo3 = TipoItem(nombreTipo = "bebidas")
        tipoItemDao.insertAll(tipo1, tipo2, tipo3)

        // *** INSERTANDO ITEMS ***
        val item1 = Item(idTipo = 1, nombreItem = "Hamburgesa doble", descripcionItem = "Deliciosa hamburgesa doble", precioItem = 5.6)
        val item2 = Item(idTipo = 2, nombreItem = "Pizza personal", descripcionItem = "Pizza para una persona", precioItem = 7.3)
        val item3 = Item(idTipo = 3, nombreItem = "Soda generica", descripcionItem = "una soda sin marca", precioItem = 1.5)
        itemDao.insertItem(item1, item2, item3)

        // *** INSERTANDO MENU ***
        val menu1 = Menu(nombreMenu = "Menu principal", descripcionMenu = "El menu principal del restaurante", fechaCreado = Date(), disponible = 1)
        menuDao.insertMenu(menu1)

        // *** INSERTANDO DETALLE DEL MENU ***
        val detalle1 = MenuDetalle(idMenu = 1, idItem = 1)
        val detalle2 = MenuDetalle(idMenu = 1, idItem = 2)
        val detalle3 = MenuDetalle(idMenu = 1, idItem = 3)
        menuDetalleDao.insertMenuDetalle(detalle1, detalle2, detalle3)
    }
}