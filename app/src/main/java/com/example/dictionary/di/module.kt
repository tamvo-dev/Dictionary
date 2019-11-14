package com.example.dictionary.di

import com.example.dictionary.MainViewModel
import com.example.dictionary.data.DATABASE_ANH_VIET
import com.example.dictionary.data.DATABASE_VIET_ANH
import com.example.dictionary.data.DatabaseAccess
import com.example.dictionary.ui.detail.DetailFragment
import com.example.dictionary.ui.detail.DetailViewModel
import com.example.dictionary.ui.home.HomeFragment
import com.example.dictionary.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<DatabaseAccess> {
        DatabaseAccess(get(), DATABASE_VIET_ANH)
    }

    single {
        HomeFragment()
    }

    single {
        DetailFragment()
    }

    viewModel {
        MainViewModel()
    }

    viewModel {
        DetailViewModel()
    }

    viewModel {
        HomeViewModel(get())
    }
}