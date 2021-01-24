package com.lucas.estudo.view.ui.resultados

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

class ResultadosViewModel(private val repository: IMercadoLivreRepository) : ViewModel() {

    val produtosLiveData = MutableLiveData<List<Produto>>()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getProdutos(query: String){
        repository.getProdutos(query, object : CallbackResult<List<Produto>> {
            override fun Success(result: List<Produto>) {
                produtosLiveData.value = result
                viewFlipperLiveData.value =
                    Pair(VIEW_FLIPPER_SUCCESS, null)
            }

            override fun ApiError (error: ErroApi) {
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
            if (modelClass.isAssignableFrom(ResultadosViewModel::class.java)) {
                return ResultadosViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}