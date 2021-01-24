package com.lucas.estudo.presentation.resultados

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucas.estudo.R
import com.lucas.estudo.data.repository.MercadoLivreRepository
import com.lucas.estudo.databinding.FragmentResultadosBinding
import com.lucas.estudo.presentation.main.fragments.MainAdapter

import com.lucas.estudo.presentation.main.fragments.MainViewModel

class ResultadosFragment : Fragment(R.layout.fragment_resultados) {

    private lateinit var viewModel: ResultadosViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ResultadoAdapter
    private lateinit var query: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentResultadosBinding.bind(view)

        viewModel = ViewModelProvider(this, ResultadosViewModel.ViewModelFactory(MercadoLivreRepository())).get(ResultadosViewModel::class.java)
        query = arguments?.getString("query")!!


        recyclerView = binding.recyclerViewResultados.apply {
            setItemViewCacheSize(50)
            setHasFixedSize(true)
        }

        viewModel.produtosLiveData.observe(viewLifecycleOwner, Observer { produtos ->
            produtos.let {
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = ResultadoAdapter(context, produtos)
                recyclerView.adapter = adapter
            }
        })

        viewModel.viewFlipperLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.viewFlipperResultados.displayedChild = it.first

                it.second?.let { error ->
                    binding.mensagem.textViewMessage.text = getText(error)
                    binding.mensagem.buttonMessage.setOnClickListener {
                        viewModel.getProdutos(query)
                    }
                }
            }
        })

        viewModel.getProdutos(query)
    }
}