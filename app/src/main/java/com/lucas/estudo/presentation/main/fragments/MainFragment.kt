package com.lucas.estudo.presentation.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.lucas.estudo.R
import com.lucas.estudo.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)

        binding.buttonNext.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_resultadosFragment)
        }

    }
}

