package com.pdm115gt3g2.pedidosapp.ui.pedidos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.pdm115gt3g2.pedidosapp.R
import com.pdm115gt3g2.pedidosapp.db.relaciones.ItemConCantidad

class ItemPedidosAdapter(private var mList: List<ItemConCantidad>): RecyclerView.Adapter<ItemPedidosAdapter.ViewHolder>() {

    //accediendo a los text view de la vista cardview
    class ViewHolder(itemPedidoView: View) : RecyclerView.ViewHolder(itemPedidoView), View.OnClickListener {
        val idItem: TextView = itemPedidoView.findViewById(R.id.idItemPedido)
        val nombreItem: TextView = itemPedidoView.findViewById(R.id.nombreItemPedido)
        val descripcionItem: TextView = itemPedidoView.findViewById(R.id.descripcionItemPedido)
        val tipoItem: TextView = itemPedidoView.findViewById(R.id.tipoItemPedido)
        val precioItem: TextView = itemPedidoView.findViewById(R.id.precioItemPedido)
        val cantidadItem: TextView = itemPedidoView.findViewById(R.id.cantidadItemPedido)

        init {
            itemPedidoView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val bundle = bundleOf(
                "origen" to "pedido",
                "idItem" to idItem.text,
                "nombreItem" to nombreItem.text,
                "descripcionItem" to descripcionItem.text,
                "tipoItem" to tipoItem.text,
                "precioItem" to precioItem.text,
                "cantidadItem" to cantidadItem.text
            )
            Navigation.findNavController(itemView).navigate(R.id.action_nav_pedido_to_nav_agregar_item, bundle)
        }

    }

    //accediendo a la vista: item_formulario
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPedidosAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_pedido_detalle, parent, false)
        return ItemPedidosAdapter.ViewHolder(view)
    }

    //uniendo
    override fun onBindViewHolder(holder: ItemPedidosAdapter.ViewHolder, position: Int) {
        val itemPedidoViewModel = mList[position]

        holder.idItem.text = itemPedidoViewModel.item.idItem.toString()
        holder.nombreItem.text = itemPedidoViewModel.item.nombreItem
        holder.descripcionItem.text = itemPedidoViewModel.item.descripcionItem
        holder.tipoItem.text = itemPedidoViewModel.tipo.nombreTipo
        holder.precioItem.text = itemPedidoViewModel.item.precioItem.toString()
        holder.cantidadItem.text = itemPedidoViewModel.pedidoDetalle.cantidadItem.toString()
    }

    //retornando tamaño de la lista
    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateItems(newItems: List<ItemConCantidad>) {
        mList = newItems
        notifyDataSetChanged()
    }
}