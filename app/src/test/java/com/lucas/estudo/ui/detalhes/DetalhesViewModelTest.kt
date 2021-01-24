package com.lucas.estudo.ui.detalhes

import ProdutoDescricao
import Snapshot
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lucas.estudo.R
import com.lucas.estudo.model.entidades.ErroApi
import com.lucas.estudo.model.entidades.Produto
import com.lucas.estudo.ui.mock.MockRepository
import com.lucas.estudo.view.ui.detalhes.DetalhesViewModel
import com.lucas.estudo.view.ui.main.fragments.MainViewModel
import com.lucas.estudo.view.utils.ResultViewFlipper
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetalhesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var produtosLiveDataObserver: Observer<Produto>
    @Mock
    private lateinit var produtoDescricaoLiveDataObserver: Observer<String>
    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>


    @Test
    fun `when view model getProdutosDetalhes get success then sets produtosLiveData`(){
        //Arrange
        val produto = Produto("5", "produto 5", 1.0, "https://mlb-s2-p.mlstatic.com/692721-MLB43098886200_082020-I.jpg", descricaoId = "1")
        val produtoDescricao = mutableListOf<ProdutoDescricao>()
            produtoDescricao.add(ProdutoDescricao("1", "1", "TESTE 1","TEXTO 1", Snapshot("",1,1,"")))
            produtoDescricao.add(ProdutoDescricao("2", "2", "TESTE 2","TEXTO 2", Snapshot("",1,1,"")))

        val resultSuccess = MockRepository("Success")
        resultSuccess.produto = produto
        resultSuccess.produtoDescricao = produtoDescricao

        val viewModel = DetalhesViewModel(resultSuccess)

        viewModel.produtoDetalheLiveData.observeForever(produtosLiveDataObserver)
        viewModel.produtoDescricaoLiveData.observeForever(produtoDescricaoLiveDataObserver)

        //Act
        viewModel.getProdutoDetalhe("")
        viewModel.getProdutoDescricao("", "")

        //Assert
        verify(produtosLiveDataObserver).onChanged(produto)
        verify(produtoDescricaoLiveDataObserver).onChanged(produtoDescricao.first().plain_text)

    }

    @Test
    fun `when view model getProdutosDetalhes get Server Error then sets produtosLiveData`(){
        //Arrange
        val resultSuccess = MockRepository("ServerError")

        val viewModel = DetalhesViewModel(resultSuccess)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getProdutoDetalhe("")

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(ResultViewFlipper.VIEW_FLIPPER_ERROR, R.string.error_500))

    }

    @Test
    fun `when view model getProdutosDetalhes get Api Error 400 then sets produtosLiveData`(){
        //Arrange
        val resultSuccess = MockRepository("ApiError")
        resultSuccess.errorApi = ErroApi(400, "Bad Resquest")

        val viewModel = DetalhesViewModel(resultSuccess)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getProdutoDetalhe("")

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(ResultViewFlipper.VIEW_FLIPPER_ERROR, R.string.error_400))

    }

    @Test
    fun `when view model getProdutosDetalhes get Api Error 500 then sets produtosLiveData`(){
        //Arrange
        val resultSuccess = MockRepository("ApiError")
        resultSuccess.errorApi = ErroApi(500, "Bad Resquest")

        val viewModel = DetalhesViewModel(resultSuccess)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getProdutoDetalhe("")

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(ResultViewFlipper.VIEW_FLIPPER_ERROR, R.string.error_500))

    }
}