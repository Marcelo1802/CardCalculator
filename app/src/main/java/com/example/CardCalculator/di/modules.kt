package com.example.CardCalculator.di

import android.content.Context
import com.example.CardCalculator.model.fatoresviewmodel
import com.example.CardCalculator.repository.fatoresrepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { androidContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE) }
    single { fatoresrepository(get()) }
    viewModel { fatoresviewmodel(get()) }
}