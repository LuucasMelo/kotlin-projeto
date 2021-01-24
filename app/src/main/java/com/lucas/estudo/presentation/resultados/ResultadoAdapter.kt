package com.lucas.estudo.presentation.resultados

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
import com.lucas.estudo.data.response.Produto.ResponseProdutos
import com.lucas.estudo.data.response.Produto.Results
import com.lucas.estudo.databinding.ResultadoProdutosBinding

class ResultadoAdapter(private val context: Context?, private var produtos: ResponseProdutos) : RecyclerView.Adapter<ResultadoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.resultado_produtos, parent, false)
        val binding = ResultadoProdutosBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(produtos.results[position])
    }

    override fun getItemCount(): Int {
        return produtos.results.size
    }

    class ViewHolder(view: ResultadoProdutosBinding): RecyclerView.ViewHolder(view.root){
        private val imagem: ImageView = view.imageView2
        private val nome: TextView = view.textViewTituloProduto
        private val valor: TextView = view.textViewProdutoValor
        private val cardView: CardView = view.cardViewResultadoProduto

        fun bind(data: Results){
            val options = RequestOptions()
                    .placeholder(R.drawable.giphy)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.semimg)

            val url =  data.thumbnail.replace("http","https")

            Glide.with(imagem)
                    .load(url)
                    .apply(options)
                    .into(imagem)

            nome.text = data.title
            valor.text = "R$ " + String.format("%.2f", data.price)

            cardView.setOnClickListener{
                val songBundle = Bundle()
                songBundle.putString("productId", data.id)
                Navigation.findNavController(it).navigate(R.id.action_global_detalhesFragment, songBundle)
            }
        }
    }
}