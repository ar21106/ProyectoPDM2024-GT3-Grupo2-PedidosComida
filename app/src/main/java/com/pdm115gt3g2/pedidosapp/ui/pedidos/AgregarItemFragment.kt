package com.pdm115gt3g2.pedidosapp.ui.pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pdm115gt3g2.pedidosapp.R

class AgregarItemFragment : Fragment() {

    companion object {
        fun newInstance() = AgregarItemFragment()
    }

    private val viewModel: AgregarItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_agregar_item, container, false)
    }
}