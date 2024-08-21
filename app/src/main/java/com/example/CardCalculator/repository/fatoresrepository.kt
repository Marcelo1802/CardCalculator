package com.example.CardCalculator.repository

import android.content.SharedPreferences
import com.example.CardCalculator.model.fatores

class fatoresrepository (private val sharedPreferences: SharedPreferences) {

    fun savefatores(fatores: fatores) {
        val editor = sharedPreferences.edit()
        val totalSimulacoes = sharedPreferences.getInt("totalSimulacoes", 0)

        editor.putFloat("valorinicial_$totalSimulacoes", fatores.valorinicial)
        editor.putFloat("taxajuros_$totalSimulacoes", fatores.taxajuros)
        editor.putFloat("aporte_$totalSimulacoes", fatores.aporte)
        editor.putInt("tempo_$totalSimulacoes", fatores.tempo)
        editor.putFloat("resultado_$totalSimulacoes", fatores.result)

        editor.putInt("totalSimulacoes", totalSimulacoes + 1)
        editor.apply()
    }

    fun getfatores(): MutableList<fatores> {
        val fatoresList = mutableListOf<fatores>()
        val totalSimulacoes = sharedPreferences.getInt("totalSimulacoes", 0)

        for (i in 0 until totalSimulacoes) {
            val valorinicial = sharedPreferences.getFloat("valorinicial_$i", 0f)
            val taxajuros = sharedPreferences.getFloat("taxajuros_$i", 0f)
            val aporte = sharedPreferences.getFloat("aporte_$i", 0f)
            val tempo = sharedPreferences.getInt("tempo_$i", 0)
            val resultado = sharedPreferences.getFloat("resultado_$i", 0F)

            fatoresList.add(fatores(valorinicial, taxajuros, aporte, tempo, resultado))
        }

        return fatoresList
    }

    fun deletefator(index: Int) {
        val totalSimulacoes = sharedPreferences.getInt("totalSimulacoes", 0)
        val editor = sharedPreferences.edit()

        if (index < 0 || index >= totalSimulacoes) {
            return
        }

        for (i in index until totalSimulacoes - 1) {
            val nextIndex = i + 1
            editor.putFloat("valorinicial_$i", sharedPreferences.getFloat("valorinicial_$nextIndex", 0f))
            editor.putFloat("taxajuros_$i", sharedPreferences.getFloat("taxajuros_$nextIndex", 0f))
            editor.putFloat("aporte_$i", sharedPreferences.getFloat("aporte_$nextIndex", 0f))
            editor.putInt("tempo_$i", sharedPreferences.getInt("tempo_$nextIndex", 0))
            editor.putFloat("resultado_$i", sharedPreferences.getFloat("resultado_$nextIndex", 0f))
        }

        editor.remove("valorinicial_${totalSimulacoes - 1}")
        editor.remove("taxajuros_${totalSimulacoes - 1}")
        editor.remove("aporte_${totalSimulacoes - 1}")
        editor.remove("tempo_${totalSimulacoes - 1}")
        editor.remove("resultado_${totalSimulacoes - 1}")

        editor.putInt("totalSimulacoes", totalSimulacoes - 1)
        editor.apply()
    }
}

