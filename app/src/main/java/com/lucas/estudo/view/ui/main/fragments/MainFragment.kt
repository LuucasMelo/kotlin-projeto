package com.lucas.estudo.view.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucas.estudo.R
import com.lucas.estudo.model.repository.MercadoLivreRepository
import com.lucas.estudo.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)

        viewModel = ViewModelProvider(this, MainViewModel.ViewModelFactory(MercadoLivreRepository())).get(MainViewModel::class.java)

        recyclerView = binding.recyclerViewProduto.apply {
            setItemViewCacheSize(50)
            setHasFixedSize(true)
        }

        viewModel.produtosLiveData.observe(viewLifecycleOwner, Observer { produtos ->
            produtos?.let {
                recyclerView.layoutManager = GridLayoutManager(context, 2)
                adapter = MainAdapter(context, produtos)
                recyclerView.adapter = adapter
            }
        })

        viewModel.viewFlipperLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.viewFlipperMain.displayedChild = it.first

                it.second?.let { error ->
                    binding.mensagem.textViewMessage.text = getText(error)
                    binding.mensagem.buttonMessage.setOnClickListener {
                        viewModel.getProdutosVendedor()
                    }
                }
            }
        })

        viewModel.getProdutosVendedor()
    }
}

