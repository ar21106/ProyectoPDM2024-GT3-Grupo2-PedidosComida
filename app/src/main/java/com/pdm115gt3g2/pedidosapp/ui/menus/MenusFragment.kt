package com.pdm115gt3g2.pedidosapp.ui.menus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.pdm115gt3g2.pedidosapp.databinding.FragmentMenusBinding

class MenusFragment : Fragment() {

    private var _binding: FragmentMenusBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val menusViewModel =
            ViewModelProvider(this).get(MenusViewModel::class.java)

        _binding = FragmentMenusBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMenus
        menusViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}