/*package com.example.uana.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uana.MainViewModel
import com.example.uana.databinding.CardLayoutBinding
import com.example.uana.databinding.CategoriaLayoutBinding

class CategoriaAdapter(

    val mainViewModel: MainViewModel

) : RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {


    class CategoriaViewHolder(val binding: CategoriaLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        return CategoriaViewHolder(
            CategoriaLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = listCategoria[position]

        holder.binding.te

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}

*/
