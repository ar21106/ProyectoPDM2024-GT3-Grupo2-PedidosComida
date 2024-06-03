package com.pdm115gt3g2.pedidosapp.ui.pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm115gt3g2.pedidosapp.R
import com.pdm115gt3g2.pedidosapp.databinding.FragmentPedidosBinding
import java.math.BigDecimal
import java.math.RoundingMode

class PedidosFragment : Fragment() {

    //para usar el view model y el adapter para el recycler view
    private lateinit var pedidosViewModel: PedidosViewModel
    private lateinit var adapter: ItemPedidosAdapter

    //predeterminado
    private var _binding: FragmentPedidosBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPedidosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // iniciando el view model usando AndroidViewModel
        pedidosViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(PedidosViewModel::class.java)

        val btnAgregar: Button = binding.btnHacerPedido
        btnAgregar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_pedido_to_nav_direccion)
        }

        val accion = arguments?.getString("accion")
        if (!accion.equals("enviar_pedido")) return root

        btnAgregar.visibility = View.INVISIBLE

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //usando el recycler view
        val recyclerView = binding.recycleViewPedidoItems
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ItemPedidosAdapter(listOf())
        recyclerView.adapter = adapter

        //detalles generales del pedido
        val estadoPedido: TextView = binding.txtEstado
        val totalPedido: TextView = binding.txtTotal
        val totalPropina: TextView = binding.txtPropina

        pedidosViewModel.pedidoConItems.observe(viewLifecycleOwner){pedidos->
            pedidos?.let {
                estadoPedido.text = it.pedido.estadoPedido.nombreEstado
                totalPropina.text = it.pedido.pedido.propina.toString()
                var t = 0.00

                for (item in it.items){
                    t += item.item.precioItem * item.pedidoDetalle.cantidadItem
                }

                val total = BigDecimal(t.toString()).setScale(2,RoundingMode.HALF_UP)
                totalPedido.text = total.toString()

            }
        }

        //observando la data del viewmodel a tiempo real
        //se manejan valores nulos porque al inicio la base de datos no tiene data, esta se agrega en segundo plano con el worker
        pedidosViewModel.pedidoItems.observe(viewLifecycleOwner) { items ->
            items?.let {
                adapter.updateItems(it)
            } ?: run {
                adapter.updateItems(emptyList())
            }
        }
    }
}