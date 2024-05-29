package com.pdm115gt3g2.pedidosapp.ui.menus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdm115gt3g2.pedidosapp.R
import com.pdm115gt3g2.pedidosapp.db.menus.Menu

class MenuAdapter(private var mList: List<Menu>): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    //accediendo a los text view de la vista: cardview_menus
    class ViewHolder(menusView: View) : RecyclerView.ViewHolder(menusView) {
        val nombreMenu: TextView = menusView.findViewById(R.id.nombreMenu)
        val descripcionMenu: TextView = menusView.findViewById(R.id.descripcionMenu)
    }

    //accediendo a la vista: menu_formulario
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_menu, parent, false)
        return MenuAdapter.ViewHolder(view)
    }

    //uniendo
    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        val menuViewModel = mList[position]

        holder.nombreMenu.text = menuViewModel.nombreMenu
        holder.descripcionMenu.text = menuViewModel.descripcionMenu
    }

    //retornando tamaño de la lista
    override fun getItemCount(): Int {
        return mList.size
    }

    fun update(newMenus: List<Menu>) {
        mList = newMenus
        notifyDataSetChanged()
    }
}