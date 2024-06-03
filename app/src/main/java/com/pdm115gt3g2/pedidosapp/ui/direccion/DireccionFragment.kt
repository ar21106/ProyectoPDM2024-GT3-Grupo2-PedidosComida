package com.pdm115gt3g2.pedidosapp.ui.direccion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pdm115gt3g2.pedidosapp.R
import com.pdm115gt3g2.pedidosapp.databinding.FragmentDireccionBinding
import com.pdm115gt3g2.pedidosapp.db.pedidos.DireccionEntrega

class DireccionFragment : Fragment() {

    private lateinit var direccionViewModel: DireccionViewModel

    //predeterminado
    private var _binding: FragmentDireccionBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDireccionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // iniciando el view model usando AndroidViewModel
        direccionViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
            DireccionViewModel::class.java)

        //leyendo argumentos
        val nombreDireccion: TextView = binding.txtDireccionNombre
        val nombreCalle: TextView = binding.txtDireccionCalle
        val coloniaBarrio: TextView = binding.txtDireccionColonia
        val numCasa: TextView = binding.txtDireccionCasa
        val departamento: Spinner = binding.spnDepartamento
        val municipio: TextView = binding.txtDireccionMunicipio
        val referencia: TextView = binding.txtDireccionReferencia

        val btnAgregar: Button = binding.btnAgregarDireccion
        btnAgregar.setOnClickListener{
            val nuevaDireccion = DireccionEntrega(
                nombreDireccion.text.toString(),
                nombreCalle.text.toString(),
                coloniaBarrio.text.toString(),
                numCasa.text.toString(),
                departamento.selectedItem.toString(),
                municipio.text.toString(),
                referencia.text.toString())

            direccionViewModel.pedidoRepository.actualizarEstado(1,2)
            direccionViewModel.direccionRepository.insertar(nuevaDireccion)

            val bundle = bundleOf(
                "accion" to "enviar_pedido"
            )
            findNavController().navigate(R.id.action_nav_direccion_to_nav_pedido, bundle)
        }


        return root
    }
}