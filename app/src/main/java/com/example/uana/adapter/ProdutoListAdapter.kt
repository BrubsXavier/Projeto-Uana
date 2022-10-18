package com.example.uana.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uana.MainViewModel
import com.example.uana.databinding.CardLayout2Binding
import com.example.uana.databinding.CardLayoutBinding
import com.example.uana.databinding.DescricaoLayoutBinding
import com.example.uana.model.Produto

class ProdutoListAdapter(
    val produtoClickListener: ProdutoClickListener,
    val mainViewModel: MainViewModel,
    val context: Context
) : RecyclerView.Adapter<ProdutoListAdapter.ProdutoListViewHolder>(

) {

    private var listProduto = emptyList<Produto>()

    class ProdutoListViewHolder(val binding: CardLayout2Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoListViewHolder {
        return ProdutoListViewHolder(
            CardLayout2Binding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProdutoListViewHolder, position: Int) {
        val produto = listProduto[position]

        holder.binding.textNome.text = produto.nome
        holder.binding.textPreco.text = produto.preco
        holder.binding.textQuantidade.text = produto.quantidade.toString()

        holder.binding.buttonAdd.setOnClickListener {

            produto.addQuantidade()
            notifyItemChanged(position)
        }

        holder.binding.buttonRem.setOnClickListener {

            produto.remQuantidade()
            notifyItemChanged(position)
        }

        holder.itemView.setOnClickListener {
            produtoClickListener.onProdutoClickListener(produto)
        }


        holder.binding.buttonAdicionar.setOnClickListener {

        }


        Glide
            .with(context)
            .load(produto.imagemProduto)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .into(holder.binding.imageLink)



    }

    override fun getItemCount(): Int {
        return listProduto.size
    }

    fun setList(list: List<Produto>) {

        listProduto = list.sortedByDescending { it.id }
        notifyDataSetChanged()
    }

}