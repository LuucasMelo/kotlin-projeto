package com.lucas.estudo.data.model

import java.lang.Exception

data class ErroApi(val codigo: Int, val message: String, var e: Exception? = null)