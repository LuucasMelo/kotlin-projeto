package com.lucas.estudo.model

import com.lucas.estudo.model.entidades.ErroApi

interface CallbackBase<T> {
    fun onSuccess(result: T)
    fun onError(error: ErroApi)

}