package com.lucas.estudo.presentation.detalhes

import ResponseProdutoDetalhe
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lucas.estudo.data.CallbackBase
import com.lucas.estudo.data.model.ErroApi
import com.lucas.estudo.data.repository.MercadoLivreRepository
import com.lucas.estudo.data.response.Produto.ResponseProdutos

class DetalhesViewModel(private val repository: MercadoLivreRepository): ViewModel() {
    val produtoDetalheLiveData = MutableLiveData<ResponseProdutoDetalhe>()

    fun getProdutoDetalhe(productId: String){
        repository.getProdutoDetalhe(productId, object : CallbackBase<ResponseProdutoDetalhe> {
            override fun onSuccess(result: ResponseProdutoDetalhe) {
                produtoDetalheLiveData.value = result
            }

            override fun onError(error: ErroApi) {
                TODO("Not yet implemented")
            }

        })
    }

    class ViewModelFactory(private val dataSource: MercadoLivreRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetalhesViewModel::class.java)) {
                return DetalhesViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}