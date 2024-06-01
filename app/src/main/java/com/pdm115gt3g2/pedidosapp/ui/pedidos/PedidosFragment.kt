package com.pdm115gt3g2.pedidosapp.ui.pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm115gt3g2.pedidosapp.databinding.FragmentPedidosBinding

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

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //usando el recycler view
        val recyclerView = binding.recycleViewPedidoItems
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ItemPedidosAdapter(listOf())
        recyclerView.adapter = adapter

        //observando la data del viewmodel a tiempo real
        //se manejan valores nulos porque al inicio la base de datos no tiene data, esta se agrega en segundo plano con el worker
        pedidosViewModel.pedidoItems.observe(viewLifecycleOwner) { items ->
            items?.let {
                adapter.updateItems(it)
            } ?: run {
                // Handle null or empty case if needed
                adapter.updateItems(emptyList())
            }
        }
    }
}