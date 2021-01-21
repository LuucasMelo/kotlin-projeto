package com.lucas.estudo.data.model

import java.lang.Exception

data class ErroApi(val codigo: String, val message: String, var e: Exception? = null)