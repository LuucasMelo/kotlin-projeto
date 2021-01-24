package com.lucas.estudo.ui.main

import ProdutoDescricao
import ResponseProdutoDetalhe
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lucas.estudo.R
import com.lucas.estudo.model.CallbackBase
import com.lucas.estudo.model.CallbackResult
import com.lucas.estudo.model.entidades.ErroApi
import com.lucas.estudo.model.entidades.Produto
import com.lucas.estudo.model.entidades.response.Produto.ResponseProdutos
import com.lucas.estudo.model.repository.IMercadoLivreRepository
import com.lucas.estudo.ui.mock.MockRepository
import com.lucas.estudo.view.ui.main.fragments.MainViewModel
import com.lucas.estudo.view.utils.ResultViewFlipper.Companion.VIEW_FLIPPER_ERROR
import com.lucas.estudo.view.utils.ResultViewFlipper.Companion.VIEW_FLIPPER_SUCCESS
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var produtosLiveDataObserver: Observer<List<Produto>>
    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>


    @Test
    fun `when view model getProdutosVendedor get success then sets produtosLiveData`(){
        //Arrange
        val produtos = mutableListOf<Produto>()
        produtos.add(Produto("1", "produto 1", 1.0, "https://mlb-s2-p.mlstatic.com/692721-MLB43098886200_082020-I.jpg", ""))
        produtos.add(Produto("2", "produto 2", 2.0, "https://mlb-s2-p.mlstatic.com/692721-MLB43098886200_082020-I.jpg", ""))

        val resultSuccess = MockRepository("Success")
        resultSuccess.produtos = produtos

        val viewModel = MainViewModel(resultSuccess)

        viewModel.produtosLiveData.observeForever(produtosLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getProdutosVendedor()

        //Assert
        verify(produtosLiveDataObserver).onChanged(produtos)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(VIEW_FLIPPER_SUCCESS, null))

    }

    @Test
    fun `when view model getProdutosVendedor get Server Error then sets produtosLiveData`(){
        //Arrange
        val resultSuccess = MockRepository("ServerError")

        val viewModel = MainViewModel(resultSuccess)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getProdutosVendedor()

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(VIEW_FLIPPER_ERROR, R.string.error_500))

    }

    @Test
    fun `when view model getProdutosVendedor get Api Error 400 then sets produtosLiveData`(){
        //Arrange
        val resultSuccess = MockRepository("ApiError")
        resultSuccess.errorApi = ErroApi(400, "Bad Resquest")

        val viewModel = MainViewModel(resultSuccess)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getProdutosVendedor()

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(VIEW_FLIPPER_ERROR, R.string.error_400))

    }

    @Test
    fun `when view model getProdutosVendedor get Api Error 500 then sets produtosLiveData`(){
        //Arrange
        val resultSuccess = MockRepository("ApiError")
        resultSuccess.errorApi = ErroApi(500, "Bad Resquest")

        val viewModel = MainViewModel(resultSuccess)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getProdutosVendedor()

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(VIEW_FLIPPER_ERROR, R.string.error_500))

    }
}



