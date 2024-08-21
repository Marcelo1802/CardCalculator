package com.example.CardCalculator.model

import java.io.Serializable

data class fatores (
    val valorinicial: Float,
    val taxajuros: Float,
    val aporte: Float,
    val tempo: Int,
    val result: Float
): Serializable