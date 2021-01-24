package com.lucas.estudo.view.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.lucas.estudo.R
import com.lucas.estudo.databinding.ItemProdutoBinding
import com.lucas.estudo.model.entidades.Produto
import com.lucas.estudo.model.entidades.response.Produto.ResponseProdutos
import com.lucas.estudo.model.entidades.response.Produto.Results

class MainAdapter(private val context: Context?, private var produtos: List<Produto>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false)
        val binding = ItemProdutoBinding.bind(view)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(produtos[position])
    }

    override fun getItemCount(): Int {
        return produtos.size
    }


    class ViewHolder(view: ItemProdutoBinding): RecyclerView.ViewHolder(view.root){
        private val imagem: ImageView = view.imagemProduto
        private val nome: TextView = view.textViewNome
        private val valor: TextView = view.textViewValorProduto
        private val cardView: CardView = view.cardProdutoDestaque

        fun bind(data: Produto){
            val options = RequestOptions()
                .placeholder(R.drawable.giphy)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.semimg)

            val url =  data.thumbnail.replace("http://","https://")

            Glide.with(imagem)
                .load(url)
                .apply(options)
                .into(imagem)

            nome.text = data.nome
            valor.text = "R$ " + String.format("%.2f", data.preco)

            cardView.setOnClickListener{
                val songBundle = Bundle()
                songBundle.putString("productId", data.id)
                Navigation.findNavController(it).navigate(R.id.action_global_detalhesFragment, songBundle)
            }
        }
    }

}