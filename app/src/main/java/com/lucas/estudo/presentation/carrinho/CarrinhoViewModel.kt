package com.lucas.estudo.presentation.carrinho

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.estudo.R
import com.lucas.estudo.data.singleton.SingletonCarrinho
import com.lucas.estudo.presentation.ResultViewFlipper.VIEW_FLIPPER_ERROR
import com.lucas.estudo.presentation.ResultViewFlipper.VIEW_FLIPPER_SUCCESS

class CarrinhoViewModel: ViewModel() {
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun verificarCarrinho(){
        when{
            SingletonCarrinho.produtos.size > 0 -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_SUCCESS, null)
            else -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.carrinho_vazio)
        }
    }
}