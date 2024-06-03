package com.pdm115gt3g2.pedidosapp.ui.inicio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm115gt3g2.pedidosapp.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {

    //para usar el view model y el adapter para el recycler view
    private lateinit var inicioViewModel: InicioViewModel
    private lateinit var adapter: ItemAdapter

    //predeterminado
    private var _binding: FragmentInicioBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // iniciando el view model usando AndroidViewModel
        inicioViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(InicioViewModel::class.java)

        //leyendo argumentos
        val origen = arguments?.getString("origen")
        var id = arguments?.getString("id")

        //si no se abrio la vista desde la seleccion de menu, se abrirá el menu id 1
        if (!origen.equals("menu")) id = "1"

        //si no: establecemos la id del menu correspondiente
        inicioViewModel.setId(id.toString().toInt())

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //usando el recycler view
        val recyclerView = binding.recycleViewItems
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ItemAdapter(listOf())
        recyclerView.adapter = adapter

        //observando la data del viewmodel a tiempo real
        //se manejan valores nulos porque al inicio la base de datos no tiene data, esta se agrega en segundo plano con el worker
        inicioViewModel.menuItems.observe(viewLifecycleOwner) { items ->
            items?.let {
                adapter.updateItems(it)
            } ?: run {
                // Handle null or empty case if needed
                adapter.updateItems(emptyList())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}