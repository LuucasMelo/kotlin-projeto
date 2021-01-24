package com.lucas.estudo.presentation.detalhes

import ResponseProdutoDetalhe
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lucas.estudo.R
import com.lucas.estudo.data.CallbackBase
import com.lucas.estudo.data.model.ErroApi
import com.lucas.estudo.data.repository.MercadoLivreRepository
import com.lucas.estudo.presentation.ResultViewFlipper.VIEW_FLIPPER_ERROR
import com.lucas.estudo.presentation.ResultViewFlipper.VIEW_FLIPPER_SUCCESS


class DetalhesViewModel(private val repository: MercadoLivreRepository): ViewModel() {
    val produtoDetalheLiveData = MutableLiveData<ResponseProdutoDetalhe>()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getProdutoDetalhe(productId: String){
        repository.getProdutoDetalhe(productId, object : CallbackBase<ResponseProdutoDetalhe> {
            override fun onSuccess(result: ResponseProdutoDetalhe) {
                produtoDetalheLiveData.value = result
                viewFlipperLiveData.value =
                    Pair(VIEW_FLIPPER_SUCCESS, null)
            }

            override fun onError(error: ErroApi) {
                when (error.codigo) {
                    400 -> {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.error_400)
                    }
                    500 -> {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.error_500)
                    }
                }
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