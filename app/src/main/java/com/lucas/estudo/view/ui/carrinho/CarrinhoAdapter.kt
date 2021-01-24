package com.lucas.estudo.view.ui.carrinho

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.lucas.estudo.R
import com.lucas.estudo.model.CallbackBase
import com.lucas.estudo.model.entidades.Produto
import com.lucas.estudo.model.entidades.singleton.SingletonCarrinho
import com.lucas.estudo.databinding.ItensCarrinhoBinding


class CarrinhoAdapter(private var context: Context?, var callback: CallbackBase<Boolean>): RecyclerView.Adapter<CarrinhoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itens_carrinho, parent, false)
        val binding = ItensCarrinhoBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(SingletonCarrinho.produtos[position], callback)
    }

    override fun getItemCount(): Int {
        return SingletonCarrinho.produtos.size
    }

    class ViewHolder(view: ItensCarrinhoBinding): RecyclerView.ViewHolder(view.root){
        private val imagem: ImageView = view.imageViewProdutoCarrinho
        private val nome: TextView = view.textViewNomeProdutoCarrinho
        private val valor: TextView = view.textViewValorProdutoCarrinho
        private val adicionar: RelativeLayout = view.buttonAdicionarQuantidade
        private val remover: RelativeLayout = view.buttonRemoverQuantidade
        private val quantidade: TextView = view.quantidadeProduto

        fun bind(data: Produto, callback: CallbackBase<Boolean>){

            quantidade.text = data.quantidade.toString()
            nome.text = data.nome
            valor.text = "R$ " + String.format("%.2f", data.preco * data.quantidade)

            adicionar.setOnClickListener {
                SingletonCarrinho.adicionarProduto(data)
                callback.onSuccess(true)
            }

            remover.setOnClickListener {
                SingletonCarrinho.removerProduto(data)
                callback.onSuccess(true)
            }

            val options = RequestOptions()
                .placeholder(R.drawable.giphy)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.semimg)

            val url =  data.thumbnail

            Glide.with(imagem)
                .load(url)
                .apply(options)
                .into(imagem)
        }
    }
}