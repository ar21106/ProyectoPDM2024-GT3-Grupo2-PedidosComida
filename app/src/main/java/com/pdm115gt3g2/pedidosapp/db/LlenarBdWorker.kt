package com.pdm115gt3g2.pedidosapp.db

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
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
import com.pdm115gt3g2.pedidosapp.db.pedidos.EstadoPedido
import com.pdm115gt3g2.pedidosapp.db.pedidos.Pedido
import com.pdm115gt3g2.pedidosapp.db.pedidos.PedidoDetalle
import java.util.Date


class LlenarBdWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val db = PedidosAppDataBase.getDatabase(applicationContext)
        populateDatabase(db.ItemDao(), db.MenuDao(), db.MenuDetalleDao(), db.TipoItemDao(),db.PedidoDao(),db.PedidoDetalleDao(),db.EstadoPedidoDao())
        return Result.success()
    }

    private suspend fun populateDatabase(
        itemDao: ItemDao,
        menuDao: MenuDao,
        menuDetalleDao: MenuDetalleDao,
        tipoItemDao: TipoItemDao,
        pedidoDao: PedidoDao,
        pedidoDetalleDao: PedidoDetalleDao,
        estadoPedidoDao: EstadoPedidoDao

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
        val menu2 = Menu(nombreMenu = "Menu secundario", descripcionMenu = "Otro menu del restaurante", fechaCreado = Date(), disponible = 1)
        menuDao.insertMenu(menu1, menu2)

        // *** INSERTANDO DETALLE DEL MENU ***
        val detalle1 = MenuDetalle(idMenu = 1, idItem = 1)
        val detalle2 = MenuDetalle(idMenu = 1, idItem = 2)
        val detalle3 = MenuDetalle(idMenu = 2, idItem = 2)
        val detalle4 = MenuDetalle(idMenu = 2, idItem = 3)
        menuDetalleDao.insertMenuDetalle(detalle1, detalle2, detalle3, detalle4)

        // *** INSERTANDO ESTADOS DE PEDIDOS ***
        val estado1 = EstadoPedido("En curso")
        val estado2 = EstadoPedido("Enviado al restaurante")
        val estado3 = EstadoPedido("En camino")
        val estado4 = EstadoPedido("Entregado y pagado")
        val estado5 = EstadoPedido("Cancelado")
        estadoPedidoDao.insertAll(estado1, estado2, estado3, estado4, estado5)

        // *** INSERTANDO PEDIDO ***
        val pedido = Pedido(1,1,1.00,Date(),30.00)
        pedidoDao.insertAll(pedido)

        // *** INSERTANDO DETALLE DEL PEDIDO ***
        val detallepedido1 = PedidoDetalle(1,3,2)
        val detallepedido2 = PedidoDetalle(1,1,3)
        val detallepedido3 = PedidoDetalle(1,2,1)
        pedidoDetalleDao.insertAll(detallepedido1, detallepedido2, detallepedido3)
    }
}