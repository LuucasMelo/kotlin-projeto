package com.lucas.estudo.presentation.main.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lucas.estudo.data.CallbackBase
import com.lucas.estudo.data.model.ErroApi
import com.lucas.estudo.data.repository.MercadoLivreRepository
import com.lucas.estudo.data.response.Produto.ResponseProdutos

class MainViewModel(private val repository: MercadoLivreRepository): ViewModel() {

    val produtosLiveData = MutableLiveData<ResponseProdutos>()

    fun getProdutos(){
        val vendedorIdTeste = "25805772"

        repository.getProdutos(vendedorIdTeste, object : CallbackBase<ResponseProdutos> {
             override fun onSuccess(result: ResponseProdutos) {
                 produtosLiveData.value = result
             }

             override fun onError(error: ErroApi) {
                 TODO("Not yet implemented")
             }

         })
    }

    class ViewModelFactory(private val dataSource: MercadoLivreRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}