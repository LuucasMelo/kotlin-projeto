package com.lucas.estudo.presentation.main.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lucas.estudo.R
import com.lucas.estudo.data.CallbackBase
import com.lucas.estudo.data.model.ErroApi
import com.lucas.estudo.data.repository.MercadoLivreRepository
import com.lucas.estudo.data.response.Produto.ResponseProdutos

class MainViewModel(private val repository: MercadoLivreRepository): ViewModel() {

    val produtosLiveData = MutableLiveData<ResponseProdutos>()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getProdutosVendedor(){
        val vendedorIdTeste = "25805772"

        repository.getProdutosVendedor(vendedorIdTeste, object : CallbackBase<ResponseProdutos> {
             override fun onSuccess(result: ResponseProdutos) {
                 produtosLiveData.value = result
                 viewFlipperLiveData.value = Pair(VIEW_FLIPPER_SUCCESS, null)
             }

             override fun onError(error: ErroApi) {
                 when(error.codigo){
                     400 -> {
                         viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.error_400)
                     }
                     500 -> {
                         viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.error_500)
                     }
                 }
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

    companion object {
        private const val VIEW_FLIPPER_SUCCESS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}