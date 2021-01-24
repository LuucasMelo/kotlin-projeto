package com.lucas.estudo.view.ui.detalhes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.lucas.estudo.R
import com.lucas.estudo.model.entidades.Produto
import com.lucas.estudo.model.repository.MercadoLivreRepository
import com.lucas.estudo.model.entidades.singleton.SingletonCarrinho
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

        viewModel.produtoDetalheLiveData.observe(viewLifecycleOwner, Observer { produtos ->
            produtos.let {
                binding.detalhesProduto.textViewNomeProduto.text = produtos.nome
                binding.detalhesProduto.textViewValor.text = "R$ " + String.format("%.2f",produtos.preco)

                adapter = ImagensAdapter(context, produtos.fotos)
                binding.detalhesProduto.viewPagerProdutos.adapter = adapter
                binding.detalhesProduto.textViewQuantidadeFoto.text = produtos.fotos.size.toString() + " Fotos"
            }

            binding.detalhesProduto.buttonAdicionar.setOnClickListener {
                SingletonCarrinho.adicionarProduto(Produto(produtos.id, produtos.nome, produtos.preco, produtos.thumbnail, produtos.descricaoId, quantidade = 1))
                Navigation.findNavController(view).navigate(R.id.action_detalhesFragment_to_carrinhoFragment)
            }
        })

        viewModel.produtoDescricaoLiveData.observe(viewLifecycleOwner, Observer { descricao ->
            binding.detalhesProduto.textViewDescricaoProduto.text = descricao
        })

        viewModel.viewFlipperLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.viewFlipperDetalhes.displayedChild = it.first

                it.second?.let { error ->
                    binding.mensagem.textViewMessage.text = getText(error)
                    binding.mensagem.buttonMessage.setOnClickListener {
                        viewModel.getProdutoDetalhe(productId)
                    }
                }
            }
        })

        viewModel.getProdutoDetalhe(productId)
    }
}