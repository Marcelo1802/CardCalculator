package com.example.CardCalculator.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.CardCalculator.R
import com.example.CardCalculator.databinding.CardFatoresBinding
import com.example.CardCalculator.model.fatores

class fatoresAdapter (

    private val onUsuarioClick: (fatores) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : ListAdapter<fatores, fatoresAdapter.MyViewHolder>(FatoresDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardFatoresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fator = getItem(position)
        holder.bind(fator)

        holder.binding.imgCard.setImageResource(getDrawableForFatores(fator))
        holder.itemView.setOnClickListener { onUsuarioClick(fator) }

        holder.binding.imgDelete.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context).apply {
                setTitle("Confirmar Exclusão")
                setMessage("Tem certeza de que deseja excluir este item?")
                setPositiveButton("Sim") { _, _ ->
                    onDeleteClick(position)
                }
                setNegativeButton("Não", null)
            }.show()
        }
    }

    class MyViewHolder(val binding: CardFatoresBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fator: fatores) {
            binding.valorInicialtextview.text = "Inicio: ${String.format("%.2f", fator.valorinicial)}"
            binding.taxaJurostextview.text = "Taxa de juros: ${String.format("%.2f", fator.taxajuros)}"
            binding.aportetextview.text = "Aporte mensal: ${String.format("%.2f", fator.aporte)}"
            binding.tempotextview.text = "Tempo: ${fator.tempo}"
            binding.txtviewResultado.text = "Resultado: ${String.format("%.2f", fator.result)}"
        }
    }

    private fun getDrawableForFatores(fator: fatores): Int {
        return when {
            fator.result > 100000 && fator.result < 1000000 -> R.drawable.money_svgrepo_com__1_
            fator.result > 1000000 -> R.drawable.money_integral_line_svgrepo_com
            else -> R.drawable.money_svgrepo_com
        }
    }
}

class FatoresDiffCallback : DiffUtil.ItemCallback<fatores>() {
    override fun areItemsTheSame(oldItem: fatores, newItem: fatores): Boolean {
        return oldItem == newItem // Define a lógica para verificar se são o mesmo item
    }

    override fun areContentsTheSame(oldItem: fatores, newItem: fatores): Boolean {
        return oldItem == newItem // Define a lógica para verificar se o conteúdo é o mesmo
    }
}
