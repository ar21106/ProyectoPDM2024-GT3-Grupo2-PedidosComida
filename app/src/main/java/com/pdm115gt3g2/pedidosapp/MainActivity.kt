package com.pdm115gt3g2.pedidosapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.pdm115gt3g2.pedidosapp.databinding.ActivityMainBinding
import com.pdm115gt3g2.pedidosapp.db.PedidosAppDataBase
import com.pdm115gt3g2.pedidosapp.db.menus.Item
import com.pdm115gt3g2.pedidosapp.db.menus.TipoItem
import com.pdm115gt3g2.pedidosapp.db.menus.Menu
import com.pdm115gt3g2.pedidosapp.db.menus.MenuDetalle
import com.pdm115gt3g2.pedidosapp.db.repositories.ItemRepositorio
import com.pdm115gt3g2.pedidosapp.db.repositories.MenuDetalleRepository
import com.pdm115gt3g2.pedidosapp.db.repositories.MenuRepository
import com.pdm115gt3g2.pedidosapp.db.repositories.TipoItemRepository
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    //repositorios para interactuar con la BD
    private lateinit var tipoItemRepository: TipoItemRepository
    private lateinit var itemRepositorio: ItemRepositorio
    private lateinit var menuRepository: MenuRepository
    private lateinit var menuDetalleRepository: MenuDetalleRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_inicio, R.id.nav_menus
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        llenarDB()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun llenarDB(){
        //iniciando base de datos
        val db = PedidosAppDataBase.getDatabase(this)

        // *** INSERTANDO TIPO DE ITEMS ***
        //accediendo al Dao
        val tipoItemDao = db.TipoItemDao()
        //iniciando repositorio de db
        tipoItemRepository = TipoItemRepository(tipoItemDao)
        //insertando tipos de items
        val tipo1 = TipoItem(nombreTipo = "hamburgesas")
        val tipo2 = TipoItem(nombreTipo = "pizzas")
        val tipo3 = TipoItem(nombreTipo = "bebidas")
        tipoItemRepository.insertar(tipo1)
        tipoItemRepository.insertar(tipo2)
        tipoItemRepository.insertar(tipo3)

        // *** INSERTANDO ITEMS ***
        val itemDao = db.ItemDao()
        itemRepositorio = ItemRepositorio(itemDao)
        val item1 = Item(idTipo = 1, nombreItem = "Hamburgesa doble", descripcionItem = "Deliciosa hamburgesa doble", precioItem = 5.6)
        val item2 = Item(idTipo = 2, nombreItem = "Pizza personal", descripcionItem = "Pizza para una persona", precioItem = 7.3)
        val item3 = Item(idTipo = 3, nombreItem = "Soda generica", descripcionItem = "una soda sin marca", precioItem = 1.5)
        itemRepositorio.insertar(item1)
        itemRepositorio.insertar(item2)
        itemRepositorio.insertar(item3)

        // *** INSERTANDO MENU ***
        val menuDao = db.MenuDao()
        menuRepository = MenuRepository(menuDao)
        val menu1 = Menu(nombreMenu = "Menu principal", descripcionMenu = "El menu principal del restaurante", fechaCreado = Date(), disponible = 1)
        menuRepository.insertar(menu1)

        // *** INSERTANDO DETALLE DEL MENU ***
        val menuDetalleDao = db.MenuDetalleDao()
        menuDetalleRepository = MenuDetalleRepository(menuDetalleDao)
        val detalle1 = MenuDetalle(idMenu = 1, idItem = 1)
        val detalle2 = MenuDetalle(idMenu = 1, idItem = 2)
        val detalle3 = MenuDetalle(idMenu = 1, idItem = 3)
        menuDetalleRepository.insertar(detalle1)
        menuDetalleRepository.insertar(detalle2)
        menuDetalleRepository.insertar(detalle3)


        //IMPORTANTE: cerrar base de datos
        db.close()
    }
}