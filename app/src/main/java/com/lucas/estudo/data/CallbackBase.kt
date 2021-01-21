package com.lucas.estudo.data

import com.lucas.estudo.data.model.ErroApi

interface CallbackBase<T> {

    fun onSuccess(result: T)
    fun onError(error: ErroApi)

}