package com.example.CardCalculator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.CardCalculator.databinding.FragmentInputBinding
import com.example.CardCalculator.model.fatoresviewmodel
import org.koin.androidx.viewmodel.ext.android.viewModel

class InputFragment : Fragment() {
    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    private val fatoresViewModel: fatoresviewmodel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            val inputValorInicial = binding.valorInicialEditText.text.toString().toFloatOrNull()
            val inputTaxaJuros = binding.taxaJurosEditText.text.toString().toFloatOrNull()
            val inputAporte = binding.aporteEditText.text.toString().toFloatOrNull()
            val inputTempo = binding.tempoEditText.text.toString().toIntOrNull()

            if (inputValorInicial != null && inputTaxaJuros != null && inputAporte != null && inputTempo != null) {
                val resultado = calcular(inputValorInicial, inputTaxaJuros, inputAporte, inputTempo)
                fatoresViewModel.savefatoresdata(inputValorInicial, inputTaxaJuros, inputAporte, resultado, inputTempo)
                Toast.makeText(requireContext(), "Simulação salva", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Por favor, insira valores válidos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calcular(inputValorInicial: Float, inputTaxaJuros: Float, inputAporte: Float, inputTempo: Int): Float {
        val taxaMensal = inputTaxaJuros / 100
        val numeroPeriodos = inputTempo

        // Cálculo do valor futuro usando a fórmula de juros compostos com aportes
        val valorFuturo = inputValorInicial * Math.pow(1 + taxaMensal.toDouble(), numeroPeriodos.toDouble()).toFloat() +
                inputAporte * ((Math.pow(1 + taxaMensal.toDouble(), numeroPeriodos.toDouble()) - 1) / taxaMensal.toDouble()).toFloat()

        return valorFuturo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
