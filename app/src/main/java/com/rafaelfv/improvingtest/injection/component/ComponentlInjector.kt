package com.rafaelfv.improvingtest.injection.component

import com.rafaelfv.improvingtest.injection.module.NetworkModule
import com.rafaelfv.improvingtest.viewModel.MainViewModel
import dagger.Component

@Component(modules = [(NetworkModule::class)])
interface ComponentlInjector {

    /**
     * Injector de dependencias para los viewModels
     */
    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder{
        fun build(): ComponentlInjector
        fun networkModule(networkModule:NetworkModule): Builder
    }
}