package com.pdm115gt3g2.pedidosapp.db

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.pdm115gt3g2.pedidosapp.db.dao.EstadoPedidoDao
import com.pdm115gt3g2.pedidosapp.db.dao.ItemDao
import com.pdm115gt3g2.pedidosapp.db.dao.MenuDao
import com.pdm115gt3g2.pedidosapp.db.dao.MenuDetalleDao
import com.pdm115gt3g2.pedidosapp.db.dao.PedidoDao
import com.pdm115gt3g2.pedidosapp.db.dao.TipoItemDao
import com.pdm115gt3g2.pedidosapp.db.menus.Item
import com.pdm115gt3g2.pedidosapp.db.menus.Menu
import com.pdm115gt3g2.pedidosapp.db.menus.MenuDetalle
import com.pdm115gt3g2.pedidosapp.db.menus.TipoItem
import com.pdm115gt3g2.pedidosapp.db.pedidos.EstadoPedido
import com.pdm115gt3g2.pedidosapp.db.pedidos.Pedido
import java.util.Date


class LlenarBdWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val db = PedidosAppDataBase.getDatabase(applicationContext)
        populateDatabase(db.ItemDao(), db.MenuDao(), db.MenuDetalleDao(), db.TipoItemDao(),db.PedidoDao(),db.EstadoPedidoDao())
        return Result.success()
    }

    private suspend fun populateDatabase(
        itemDao: ItemDao,
        menuDao: MenuDao,
        menuDetalleDao: MenuDetalleDao,
        tipoItemDao: TipoItemDao,
        pedidoDao: PedidoDao,
        estadoPedidoDao: EstadoPedidoDao

    ) {
        // *** INSERTANDO TIPO DE ITEMS ***
        val tipo1 = TipoItem(nombreTipo = "Hamburguesas")
        val tipo2 = TipoItem(nombreTipo = "Pizzas")
        val tipo3 = TipoItem(nombreTipo = "Bebidas")
        val tipo4 = TipoItem(nombreTipo = "Entradas")
        val tipo5 = TipoItem(nombreTipo = "Postres")
        val tipo6 = TipoItem(nombreTipo = "Desayunos")
        tipoItemDao.insertAll(tipo1, tipo2, tipo3, tipo4, tipo5, tipo6)

        // *** INSERTANDO ITEMS ***
        val item1 = Item(idTipo = 1, nombreItem = "Hamburgesa clásica", descripcionItem = "Deliciosa hamburgesa clásica", precioItem = 4.59)
        val item2 = Item(idTipo = 1, nombreItem = "Hamburgesa doble", descripcionItem = "Deliciosa clásica doble", precioItem = 7.39)
        val item3 = Item(idTipo = 1, nombreItem = "Hamburgesa de pollo", descripcionItem = "Deliciosa pechuga de pollo en una hamburguesa", precioItem = 5.15)

        val item4 = Item(idTipo = 2, nombreItem = "Pizza personal jamón", descripcionItem = "Pizza clásica para una persona", precioItem = 5.35)
        val item5 = Item(idTipo = 2, nombreItem = "Pizza personal peperoni", descripcionItem = "Pizza con peperoni", precioItem = 4.39)
        val item6 = Item(idTipo = 2, nombreItem = "Pizza personal hawaiiana", descripcionItem = "Pizza hawwaiiana con piña", precioItem = 5.55)

        val item7 = Item(idTipo = 3, nombreItem = "Coca-Cola", descripcionItem = "Refrezcante soda Coca-cola", precioItem = 1.55)
        val item8 = Item(idTipo = 3, nombreItem = "7-up", descripcionItem = "Refrezcante soda de limón", precioItem = 1.19)
        val item9 = Item(idTipo = 3, nombreItem = "Salva-cola", descripcionItem = "Soda hecha en El Salvador", precioItem = 0.80)

        val item10 = Item(idTipo = 4, nombreItem = "Papas fritas", descripcionItem = "Deliciosas papas a la francesa", precioItem = 1.11)
        val item11 = Item(idTipo = 4, nombreItem = "Ensalada de papa", descripcionItem = "Fresca ensalada con papa", precioItem = 2.55)
        val item12 = Item(idTipo = 4, nombreItem = "Ensalada", descripcionItem = "Ensalada con frescos vegetales y frutas", precioItem = 1.50)

        val item13 = Item(idTipo = 5, nombreItem = "Pie de manzana", descripcionItem = "Jugoso pie de manzada recien salido del horno", precioItem = 1.20)
        val item14 = Item(idTipo = 5, nombreItem = "Flan", descripcionItem = "Delicioso flan", precioItem = 0.55)
        val item15 = Item(idTipo = 5, nombreItem = "Postre de naranja", descripcionItem = "Jugoso postre hecho de naranja", precioItem = 2.59)

        val item16 = Item(idTipo = 6, nombreItem = "Desayuno tipico", descripcionItem = "Desayuno con platano y frijoles molidos", precioItem = 3.89)
        val item17 = Item(idTipo = 6, nombreItem = "Pancakes", descripcionItem = "5 pancakes con miel de maple", precioItem = 4.29)
        val item18 = Item(idTipo = 6, nombreItem = "Banana Waffles", descripcionItem = "Sabrosos waffles de banana", precioItem = 5.59)

        itemDao.insertItem(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13, item14, item15, item16, item17, item18)

        // *** INSERTANDO MENU ***
        val menu1 = Menu(nombreMenu = "Menu principal", descripcionMenu = "El menu principal del restaurante", fechaCreado = Date(), disponible = 1)
        val menu2 = Menu(nombreMenu = "Menu hamburguesas", descripcionMenu = "Combos de hamburgesas y otros platillos de comida rápida", fechaCreado = Date(), disponible = 1)
        val menu3 = Menu(nombreMenu = "Menu Pizzas", descripcionMenu = "Las mejores pizzas del país", fechaCreado = Date(), disponible = 1)
        val menu4 = Menu(nombreMenu = "Menu Desayunos", descripcionMenu = "Prueba nuestros deliciosos desayunos", fechaCreado = Date(), disponible = 1)
        val menu5 = Menu(nombreMenu = "Menu Postres", descripcionMenu = "Deleitate con nuestros mejores postres", fechaCreado = Date(), disponible = 1)
        menuDao.insertMenu(menu1, menu2, menu3, menu4, menu5)

        // *** INSERTANDO DETALLE DEL MENU ***
        val detalle1_1 = MenuDetalle(idMenu = 1, idItem = 1)
        val detalle1_2 = MenuDetalle(idMenu = 1, idItem = 2)
        val detalle1_3 = MenuDetalle(idMenu = 1, idItem = 3)
        val detalle1_4 = MenuDetalle(idMenu = 1, idItem = 4)
        val detalle1_5 = MenuDetalle(idMenu = 1, idItem = 5)
        val detalle1_6 = MenuDetalle(idMenu = 1, idItem = 6)
        val detalle1_7 = MenuDetalle(idMenu = 1, idItem = 7)
        val detalle1_8 = MenuDetalle(idMenu = 1, idItem = 8)
        val detalle1_9 = MenuDetalle(idMenu = 1, idItem = 9)
        val detalle1_10 = MenuDetalle(idMenu = 1, idItem = 10)
        val detalle1_11 = MenuDetalle(idMenu = 1, idItem = 11)
        val detalle1_12 = MenuDetalle(idMenu = 1, idItem = 12)
        val detalle1_13 = MenuDetalle(idMenu = 1, idItem = 13)
        val detalle1_14 = MenuDetalle(idMenu = 1, idItem = 14)
        val detalle1_15 = MenuDetalle(idMenu = 1, idItem = 15)
        val detalle1_16 = MenuDetalle(idMenu = 1, idItem = 16)
        val detalle1_17 = MenuDetalle(idMenu = 1, idItem = 17)
        val detalle1_18 = MenuDetalle(idMenu = 1, idItem = 18)
        menuDetalleDao.insertMenuDetalle(detalle1_1, detalle1_2, detalle1_3, detalle1_4,detalle1_5,detalle1_6,detalle1_7,detalle1_8,detalle1_9,
                detalle1_10,detalle1_11,detalle1_12,detalle1_13,detalle1_14,detalle1_15,detalle1_16,detalle1_17,detalle1_18)

        val detalle2_1 = MenuDetalle(idMenu = 2, idItem = 1)
        val detalle2_2 = MenuDetalle(idMenu = 2, idItem = 2)
        val detalle2_3 = MenuDetalle(idMenu = 2, idItem = 3)
        val detalle2_4 = MenuDetalle(idMenu = 2, idItem = 7)
        val detalle2_5 = MenuDetalle(idMenu = 2, idItem = 8)
        val detalle2_6 = MenuDetalle(idMenu = 2, idItem = 9)
        val detalle2_7 = MenuDetalle(idMenu = 2, idItem = 10)
        val detalle2_8 = MenuDetalle(idMenu = 2, idItem = 11)
        val detalle2_9 = MenuDetalle(idMenu = 2, idItem = 12)
        val detalle2_10 = MenuDetalle(idMenu = 2, idItem = 13)
        menuDetalleDao.insertMenuDetalle(detalle2_1,detalle2_2,detalle2_3,detalle2_4,detalle2_5,detalle2_6,detalle2_7,detalle2_8,detalle2_9,detalle2_10)

        val detalle3_1 = MenuDetalle(idMenu = 3, idItem = 4)
        val detalle3_2 = MenuDetalle(idMenu = 3, idItem = 5)
        val detalle3_3 = MenuDetalle(idMenu = 3, idItem = 6)
        val detalle3_4 = MenuDetalle(idMenu = 3, idItem = 7)
        val detalle3_5 = MenuDetalle(idMenu = 3, idItem = 8)
        val detalle3_6 = MenuDetalle(idMenu = 3, idItem = 9)
        val detalle3_7 = MenuDetalle(idMenu = 3, idItem = 10)
        val detalle3_8 = MenuDetalle(idMenu = 3, idItem = 11)
        val detalle3_9 = MenuDetalle(idMenu = 3, idItem = 12)
        val detalle3_10 = MenuDetalle(idMenu = 3, idItem = 14)
        menuDetalleDao.insertMenuDetalle(detalle3_1,detalle3_2,detalle3_3,detalle3_4,detalle3_5,detalle3_6,detalle3_7,detalle3_8,detalle3_9,detalle3_10)

        val detalle4_1 = MenuDetalle(idMenu = 4, idItem = 16)
        val detalle4_2 = MenuDetalle(idMenu = 4, idItem = 17)
        val detalle4_3 = MenuDetalle(idMenu = 4, idItem = 18)
        val detalle4_4 = MenuDetalle(idMenu = 4, idItem = 7)
        val detalle4_5 = MenuDetalle(idMenu = 4, idItem = 8)
        val detalle4_6 = MenuDetalle(idMenu = 4, idItem = 9)
        menuDetalleDao.insertMenuDetalle(detalle4_1,detalle4_2,detalle4_3,detalle4_4,detalle4_5,detalle4_6)

        val detalle5_1 = MenuDetalle(idMenu = 5, idItem = 13)
        val detalle5_2 = MenuDetalle(idMenu = 5, idItem = 14)
        val detalle5_3 = MenuDetalle(idMenu = 5, idItem = 15)
        val detalle5_4 = MenuDetalle(idMenu = 5, idItem = 7)
        val detalle5_5 = MenuDetalle(idMenu = 5, idItem = 8)
        val detalle5_6 = MenuDetalle(idMenu = 5, idItem = 9)
        menuDetalleDao.insertMenuDetalle(detalle5_1,detalle5_2,detalle5_3,detalle5_4,detalle5_5,detalle5_6)

        // *** INSERTANDO ESTADOS DE PEDIDOS ***
        val estado1 = EstadoPedido("No enviado")
        val estado2 = EstadoPedido("Enviado al restaurante")
        val estado3 = EstadoPedido("En camino")
        val estado4 = EstadoPedido("Entregado y pagado")
        estadoPedidoDao.insertAll(estado1, estado2, estado3, estado4)

        // *** INSERTANDO PEDIDO ***
        val pedido = Pedido(1,1,1.00,Date(),0.00)
        pedidoDao.insertAll(pedido)
    }
}