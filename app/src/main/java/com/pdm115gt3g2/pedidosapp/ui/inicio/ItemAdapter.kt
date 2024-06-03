package com.pdm115gt3g2.pedidosapp.ui.inicio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pdm115gt3g2.pedidosapp.R
import com.pdm115gt3g2.pedidosapp.db.relaciones.ItemConTipoItem

class ItemAdapter(private var mList: List<ItemConTipoItem>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    //accediendo a los text view de la vista: cardview_item
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val idItem: TextView = itemView.findViewById(R.id.idItem)
        val nombreItem: TextView = itemView.findViewById(R.id.nombreItem)
        val descripcionItem: TextView = itemView.findViewById(R.id.descripcionItem)
        val tipoItem: TextView = itemView.findViewById(R.id.tipoItem)
        val precioItem: TextView = itemView.findViewById(R.id.precioItem)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val bundle = bundleOf(
                "origen" to "menu",
                "idItem" to idItem.text,
                "nombreItem" to nombreItem.text,
                "descripcionItem" to descripcionItem.text,
                "tipoItem" to tipoItem.text,
                "precioItem" to precioItem.text,
                "cantidadItem" to "1"
            )
            findNavController(itemView).navigate(R.id.action_nav_inicio_to_nav_agregar_item, bundle)
        }
    }

    //accediendo a la vista: item_formulario
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_item, parent, false)
        return ItemAdapter.ViewHolder(view)
    }

    //uniendo
    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val itemViewModel = mList[position]

        holder.idItem.text = itemViewModel.item.idItem.toString()
        holder.nombreItem.text = itemViewModel.item.nombreItem
        holder.descripcionItem.text = itemViewModel.item.descripcionItem
        holder.tipoItem.text = itemViewModel.tipo.nombreTipo
        holder.precioItem.text = itemViewModel.item.precioItem.toString()
    }

    //retornando tamaño de la lista
    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateItems(newItems: List<ItemConTipoItem>) {
        mList = newItems
        notifyDataSetChanged()
    }
}