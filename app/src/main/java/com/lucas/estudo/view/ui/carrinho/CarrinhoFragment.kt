package com.lucas.estudo.view.ui.carrinho

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucas.estudo.R
import com.lucas.estudo.model.CallbackBase
import com.lucas.estudo.model.entidades.ErroApi
import com.lucas.estudo.model.entidades.singleton.SingletonCarrinho
import com.lucas.estudo.databinding.FragmentCarrinhoBinding

class CarrinhoFragment : Fragment(R.layout.fragment_carrinho) {

    private lateinit var adapter: CarrinhoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CarrinhoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCarrinhoBinding.bind(view)

        viewModel = ViewModelProvider(this).get(CarrinhoViewModel::class.java)

        recyclerView = binding.carrinhoProdutos.recyclerViewCarrinho.apply {
            setItemViewCacheSize(50)
            setHasFixedSize(true)
        }

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = CarrinhoAdapter(context, object : CallbackBase<Boolean>{
            override fun onSuccess(result: Boolean) {
                adapter.notifyDataSetChanged()
                binding.carrinhoProdutos.valor.text = "R$ " + String.format("%.2f", SingletonCarrinho.valor)
            }

            override fun onError(error: ErroApi) {

            }

        })
        recyclerView.adapter = adapter

        binding.carrinhoProdutos.valor.text = "R$ " + String.format("%.2f", SingletonCarrinho.valor)

        binding.carrinhoProdutos.buttonComprar.setOnClickListener {
            SingletonCarrinho.limparCarrinho()
            Navigation.findNavController(view).navigate(R.id.action_global_mainFragment)
        }


        viewModel.viewFlipperLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.viewFlipperCarrinho.displayedChild = it.first

                it.second?.let { error ->
                    binding.mensagem.textViewMessage.text = getText(error)
                    binding.mensagem.imageViewMessage.setImageResource(R.drawable.empty_cart)
                    binding.mensagem.buttonMessage.text = getString(R.string.encontrar_produto)
                    binding.mensagem.buttonMessage.setOnClickListener {
                        Navigation.findNavController(view).navigate(R.id.action_global_mainFragment)
                    }
                }
            }
        })

        viewModel.verificarCarrinho()
    }
}