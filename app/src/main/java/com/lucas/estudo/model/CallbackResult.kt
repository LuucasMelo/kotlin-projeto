package com.lucas.estudo.model

import com.lucas.estudo.model.entidades.ErroApi

interface CallbackResult<T> {
    fun Success(result: T)
    fun ApiError(error: ErroApi)
    fun ServerError()
}