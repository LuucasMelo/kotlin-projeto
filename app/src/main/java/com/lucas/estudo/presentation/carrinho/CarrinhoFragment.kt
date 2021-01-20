package com.lucas.estudo.presentation.carrinho

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.lucas.estudo.R
import com.lucas.estudo.databinding.FragmentCarrinhoBinding

class CarrinhoFragment : Fragment(R.layout.fragment_carrinho) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCarrinhoBinding.bind(view)

        binding.buttonComprar.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_global_mainFragment)
        }
    }
}