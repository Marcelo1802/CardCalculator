package com.example.CardCalculator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.CardCalculator.R
import com.example.CardCalculator.databinding.FragmentCardBinding
import com.example.CardCalculator.model.fatores
import com.example.CardCalculator.model.fatoresviewmodel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardFragment : Fragment() {

    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

    private val fatoresViewModel: fatoresviewmodel by viewModel()
    private lateinit var adapter: fatoresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val load = fatoresViewModel.loadFatores()
        initRecyclerView(load)
        setupObservers()
    }

    private fun initRecyclerView(load: Unit) {
        adapter = fatoresAdapter(
            { fator ->
                // Código para quando um item é clicado
                val bundle = Bundle().apply {
                    putSerializable("detalhes", fator)
                }
                findNavController().navigate(R.id.action_cardFragment_to_detalhesFragment, bundle)
            },
            { position ->
                // Código para quando o botão de deletar é clicado
                fatoresViewModel.deletefator(position) // Remove o fator do ViewModel
                fatoresViewModel.loadFatores() // Atualiza a lista após a deleção
            }
        )

        binding.Rcmain.layoutManager = LinearLayoutManager(requireContext())
        binding.Rcmain.adapter = adapter
    }

    private fun setupObservers() {
        fatoresViewModel.fatoresList.observe(viewLifecycleOwner) { fatoresList ->
            adapter.submitList(fatoresList)
        }
    }

//    override fun onResume() {
//        super.onResume()
//        fatoresViewModel.loadFatores() // Força a atualização da lista ao voltar para o fragmento
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
