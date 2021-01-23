package com.lucas.estudo.presentation.main

import ResponseProdutoDetalhe
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lucas.estudo.data.CallbackBase
import com.lucas.estudo.data.repository.IMercadoLivreRepository
import com.lucas.estudo.data.repository.MercadoLivreRepository
import com.lucas.estudo.data.response.Produto.ResponseProdutos
import com.lucas.estudo.presentation.main.fragments.MainViewModel
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Test
    fun `when view model getProdutosVendedor get success then sets produtosLiveData`(){
        //Arrange
        viewModel = MainViewModel(MockRepository())

        //Act

        //Assert
    }
}

class MockRepository(private lateinit var result: CallbackBase<>) : IMercadoLivreRepository {
    override fun getProdutosVendedor(sellerId: String, produtosCallback: CallbackBase<ResponseProdutos>) {
        TODO("Not yet implemented")
    }

    override fun getProdutos(query: String, produtosCallback: CallbackBase<ResponseProdutos>) {
        TODO("Not yet implemented")
    }

    override fun getProdutoDetalhe(
        productId: String,
        produtosCallback: CallbackBase<ResponseProdutoDetalhe>
    ) {
        TODO("Not yet implemented")
    }

}