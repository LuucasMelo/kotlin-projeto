package com.lucas.estudo.ui.resultados

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lucas.estudo.R
import com.lucas.estudo.model.entidades.ErroApi
import com.lucas.estudo.model.entidades.Produto
import com.lucas.estudo.ui.mock.MockRepository
import com.lucas.estudo.view.ui.main.fragments.MainViewModel
import com.lucas.estudo.view.ui.resultados.ResultadosViewModel
import com.lucas.estudo.view.utils.ResultViewFlipper
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResultadosViewModelTeste {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var produtosLiveDataObserver: Observer<List<Produto>>
    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    @Test
    fun `when view model getProdutos get success then sets produtosLiveData`(){
        //Arrange
        val produtos = mutableListOf<Produto>()
        produtos.add(Produto("3", "produto 3", 3.0, "https://mlb-s2-p.mlstatic.com/692721-MLB43098886200_082020-I.jpg", ""))
        produtos.add(Produto("4", "produto 4", 4.0, "https://mlb-s2-p.mlstatic.com/692721-MLB43098886200_082020-I.jpg", ""))

        val resultSuccess = MockRepository("Success")
        resultSuccess.produtos = produtos

        val viewModel = ResultadosViewModel(resultSuccess)

        viewModel.produtosLiveData.observeForever(produtosLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getProdutos("teste")

        //Assert
        verify(produtosLiveDataObserver).onChanged(produtos)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(ResultViewFlipper.VIEW_FLIPPER_SUCCESS, null))

    }

    @Test
    fun `when view model getProdutos get Server Error then sets produtosLiveData`(){
        //Arrange
        val resultSuccess = MockRepository("ServerError")

        val viewModel = ResultadosViewModel(resultSuccess)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getProdutos("")

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(ResultViewFlipper.VIEW_FLIPPER_ERROR, R.string.error_500))

    }

    @Test
    fun `when view model getProdutos get Api Error 400 then sets produtosLiveData`(){
        //Arrange
        val resultSuccess = MockRepository("ApiError")
        resultSuccess.errorApi = ErroApi(400, "Bad Resquest")

        val viewModel = ResultadosViewModel(resultSuccess)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getProdutos("")

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(ResultViewFlipper.VIEW_FLIPPER_ERROR, R.string.error_400))

    }

    @Test
    fun `when view model getProdutos get Api Error 500 then sets produtosLiveData`(){
        //Arrange
        val resultSuccess = MockRepository("ApiError")
        resultSuccess.errorApi = ErroApi(500, "Bad Resquest")

        val viewModel = ResultadosViewModel(resultSuccess)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getProdutos("")

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(ResultViewFlipper.VIEW_FLIPPER_ERROR, R.string.error_500))

    }

}