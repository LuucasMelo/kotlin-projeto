package com.lucas.estudo.view.ui.detalhes

import Pictures
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.lucas.estudo.R
import com.lucas.estudo.databinding.ImagemProdutoBinding

class ImagensAdapter(private var context: Context?, private var imagens: List<String>): RecyclerView.Adapter<ImagensAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.imagem_produto, parent, false)
        val binding = ImagemProdutoBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imagens[position])
    }

    override fun getItemCount(): Int {
        return imagens.size
    }

    class ViewHolder(view: ImagemProdutoBinding): RecyclerView.ViewHolder(view.root){
        private val imagem: ImageView = view.imageViewProduto

        fun bind(data: String){
            val options = RequestOptions()
                    .placeholder(R.drawable.giphy)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.semimg)

            Glide.with(imagem)
                    .load(data)
                    .apply(options)
                    .into(imagem)
        }
    }

}