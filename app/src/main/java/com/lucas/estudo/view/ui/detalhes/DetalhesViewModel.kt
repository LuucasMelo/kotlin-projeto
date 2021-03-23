package com.lucas.estudo.view.ui.detalhes

import ProdutoDescricao
import ResponseProdutoDetalhe
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lucas.estudo.R
import com.lucas.estudo.model.CallbackResult
import com.lucas.estudo.model.entidades.ErroApi
import com.lucas.estudo.model.entidades.Produto
import com.lucas.estudo.model.repository.IMercadoLivreRepository
import com.lucas.estudo.model.repository.MercadoLivreRepository
import com.lucas.estudo.view.utils.ResultViewFlipper.Companion.VIEW_FLIPPER_ERROR
import com.lucas.estudo.view.utils.ResultViewFlipper.Companion.VIEW_FLIPPER_SUCCESS


class DetalhesViewModel(private val repository: IMercadoLivreRepository): ViewModel() {

    val produtoDetalheLiveData = MutableLiveData<Produto>()
    val produtoDescricaoLiveData = MutableLiveData<String>()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getProdutoDetalhe(productId: String){
        repository.getProdutoDetalhe(productId, object : CallbackResult<Produto> {
            override fun Success(result: Produto) {

                produtoDetalheLiveData.value = result
                getProdutoDescricao(productId, result.descricaoId)

                viewFlipperLiveData.value =
                    Pair(VIEW_FLIPPER_SUCCESS, null)
            }

            override fun ApiError(error: ErroApi) {
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

            override fun ServerError() {
                viewFlipperLiveData.value =
                    Pair(VIEW_FLIPPER_ERROR, R.string.error_500)
            }
        })
    }

    fun getProdutoDescricao(productId: String, descriptionId: String){
        repository.getProdutoDescricao(productId, object : CallbackResult<List<ProdutoDescricao>>{
            override fun Success(result: List<ProdutoDescricao>) {
                produtoDescricaoLiveData.value = result.find { it.id == descriptionId }?.plain_text
                viewFlipperLiveData.value =
                        Pair(VIEW_FLIPPER_SUCCESS, null)
            }

            override fun ApiError(error: ErroApi) {
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

            override fun ServerError() {
                viewFlipperLiveData.value =
                    Pair(VIEW_FLIPPER_ERROR, R.string.error_500)
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