package com.pdm115gt3g2.pedidosapp.ui.inicio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.pdm115gt3g2.pedidosapp.databinding.FragmentInicioBinding
import com.pdm115gt3g2.pedidosapp.db.repositories.MenuRepository

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null

    //repositorio para acceder a la BD
    private lateinit var menuRepository: MenuRepository

    //para usar el recycler view
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ItemAdapter.ViewHolder>? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inicioViewModel =
            ViewModelProvider(this).get(InicioViewModel::class.java)

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*
        val textView: TextView = binding.textInicio
        inicioViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        */

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO arreglar el recycler view para mostrar los items del menu principal
        /*
        //extrayendo items del menu principal de la BD
        //menu principal tiene id = 1
        val db = PedidosAppDataBase.getDatabase(requireContext())
        val menuDao = db.MenuDao()
        menuRepository = MenuRepository(menuDao)
        val menu = menuRepository.buscarPorId(1)

        //usando el recycler view
        var recyclerView = binding.recycleViewItems
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ItemAdapter(menu.items)
        recyclerView.adapter = adapter

         */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}