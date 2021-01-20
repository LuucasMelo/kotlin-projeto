package com.lucas.estudo.presentation.detalhes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.lucas.estudo.R
import com.lucas.estudo.databinding.FragmentDetalhesBinding

class DetalhesFragment : Fragment(R.layout.fragment_detalhes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetalhesBinding.bind(view)

        binding.buttonAdicionar.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_detalhesFragment_to_carrinhoFragment)
        }
    }
}