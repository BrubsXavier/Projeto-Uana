package com.example.uana.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uana.MainActivity
import com.example.uana.MainViewModel
import com.example.uana.ShoppingCart
import com.example.uana.databinding.CardLayoutBinding
import com.example.uana.databinding.FragmentListProdutoBinding
import com.example.uana.model.ItemDeCarrinho
import com.example.uana.model.Produto
import com.google.android.material.snackbar.Snackbar

class ProdutoAdapter(
    val produtoClickListener: ProdutoClickListener,
    val mainViewModel: MainViewModel,
    val context: Context
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>(


) {
    private var listProduto = emptyList<Produto>()

    class ProdutoViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        return ProdutoViewHolder(
            CardLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = listProduto[position]

        holder.binding.textNome.text = produto.nome
        holder.binding.textPreco.text = produto.preco
        holder.binding.textEstoque.text = produto.estoque.toString()


        holder.itemView.setOnClickListener {
            produtoClickListener.onProdutoClickListener(produto)
        }

        holder.binding.buttonDeletar.setOnClickListener {
            showAlertDialog(produto.id)
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

    private fun showAlertDialog(id: Long) {
        AlertDialog.Builder(context)
            .setTitle("Excluir Produto")
            .setMessage("Deseja excluir o produto?")
            .setPositiveButton("Sim") { _, _ ->
                mainViewModel.deleteProduto(id)
            }
            .setNegativeButton("Não") { _, _ ->
            }.show()
    }
}