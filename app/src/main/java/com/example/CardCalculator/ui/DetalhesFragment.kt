package com.example.CardCalculator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.CardCalculator.R
import com.example.CardCalculator.databinding.FragmentDetalhesBinding
import com.example.CardCalculator.model.fatores

class DetalhesFragment : Fragment() {

    private var _binding: FragmentDetalhesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalhesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clic()

        // passa argumentos
        val fator = arguments?.getSerializable("detalhes") as? fatores
        if (fator != null) {
            detalhes(fator)
        }
    }

    private fun detalhes(fator: fatores) {
        binding.detalhesValorinicial.text = "Valor inicial: ${String.format("%.2f", fator.valorinicial)}"
        binding.detalhesTaxaJuros.text = "Taxa de juros: ${String.format("%.2f", fator.taxajuros)}"
        binding.detalhesAporte.text = "Aporte mensal: ${String.format("%.2f", fator.aporte)}"
        binding.detalhesTempo.text = "meses: ${fator.tempo}"
        binding.detalhesResultado.text = "Resultado: ${String.format("%.2f", fator.result)}"
        binding.ImageViewDetalhes.setImageResource(getDrawableForFatores(fator))
    }

    private fun getDrawableForFatores(fator: fatores): Int {
        return when {
            fator.result > 100000 && fator.result < 1000000 -> R.drawable.money_svgrepo_com__1_
            fator.result > 1000000 -> R.drawable.money_integral_line_svgrepo_com
            else -> R.drawable.money_svgrepo_com
        }
    }

    private fun clic(){
        binding.detalhesExit.setOnClickListener {
            findNavController().navigate(R.id.action_detalhesFragment_to_cardFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}