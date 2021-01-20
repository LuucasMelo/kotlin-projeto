package com.lucas.estudo.presentation.resultados

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.lucas.estudo.R
import com.lucas.estudo.databinding.FragmentMainBinding
import com.lucas.estudo.databinding.FragmentResultadosBinding

class ResultadosFragment : Fragment(R.layout.fragment_resultados) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentResultadosBinding.bind(view)

        binding.buttonProximo.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_resultadosFragment_to_detalhesFragment)
        }
    }
}