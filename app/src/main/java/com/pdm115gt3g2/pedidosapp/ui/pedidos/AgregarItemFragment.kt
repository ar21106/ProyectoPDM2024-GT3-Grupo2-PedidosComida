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
import com.pdm115gt3g2.pedidosapp.databinding.FragmentAgregarItemBinding
import com.pdm115gt3g2.pedidosapp.db.pedidos.PedidoDetalle

class AgregarItemFragment : Fragment() {

    //para usar el view model
    private lateinit var agregarItemViewModel: AgregarItemViewModel

    //predeterminado
    private var _binding: FragmentAgregarItemBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgregarItemBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // iniciando el view model usando AndroidViewModel
        agregarItemViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
            AgregarItemViewModel::class.java)

        //leyendo argumentos
        val txtId: TextView = binding.txtAgregarItemId
        val txtNombre: TextView = binding.txtAgregarItemNombre
        val txtDescriocion: TextView = binding.txtAgregarItemDescripcion
        val txtTipo: TextView = binding.txtAgregarItemTipo
        val txtPrecio: TextView = binding.txtAgregarItemPrecio
        val txtCantidad: TextView = binding.txtAgregarItemCantidad
        txtId.text = arguments?.getString("idItem")
        txtNombre.text = arguments?.getString("nombreItem")
        txtDescriocion.text = arguments?.getString("descripcionItem")
        txtTipo.text = arguments?.getString("tipoItem")
        txtPrecio.text = arguments?.getString("precioItem")
        txtCantidad.text = arguments?.getString("cantidadItem")

        //agregar el item al pedido
        val btnAgregar: Button = binding.btnAgregarItemPedido
        btnAgregar.setOnClickListener{
            val nuevoPedidoDetalle = PedidoDetalle(1,txtId.text.toString().toInt(),txtCantidad.text.toString().toInt())
            agregarItemViewModel.pedidoDetalleRepository.insertar(nuevoPedidoDetalle)
            findNavController().popBackStack()
        }

        //determinando si se esta agregando un nuevo item al pedido o editando uno ya agregado
        val origen = arguments?.getString("origen")
        if(origen.equals("menu")) return root

        //si se esta editando: habilitando eliminacion
        val btnEliminar: Button = binding.btnEliminarItemPedido
        btnEliminar.visibility = View.VISIBLE
        btnEliminar.setOnClickListener{
            val pedidoDetalle = PedidoDetalle(1,txtId.text.toString().toInt(),0)
            agregarItemViewModel.pedidoDetalleRepository.eliminar(pedidoDetalle)
            findNavController().popBackStack()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}