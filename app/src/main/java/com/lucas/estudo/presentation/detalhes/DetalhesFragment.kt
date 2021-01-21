package com.lucas.estudo.presentation.detalhes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.lucas.estudo.R
import com.lucas.estudo.data.repository.MercadoLivreRepository
import com.lucas.estudo.databinding.FragmentDetalhesBinding

class DetalhesFragment : Fragment(R.layout.fragment_detalhes) {

    private lateinit var viewModel: DetalhesViewModel
    private lateinit var adapter: ImagensAdapter
    private lateinit var productId: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetalhesBinding.bind(view)

        viewModel = ViewModelProvider(this, DetalhesViewModel.ViewModelFactory(MercadoLivreRepository())).get(DetalhesViewModel::class.java)
        productId = arguments?.getString("productId")!!

        binding.buttonAdicionar.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_detalhesFragment_to_carrinhoFragment)
        }

        viewModel.produtoDetalheLiveData.observe(viewLifecycleOwner, Observer { produtos ->
            produtos.let {
                binding.textViewNomeProduto.text = produtos.title
                binding.textViewValor.text = "R$ " + produtos.price
                binding.textViewDescricaoProduto.text

                adapter = ImagensAdapter(context, produtos.pictures)
                binding.viewPagerProdutos.adapter = adapter
                binding.textViewQuantidadeFoto.text = produtos.pictures.size.toString() + " Fotos"
            }
        })

        viewModel.getProdutoDetalhe(productId)
    }
}