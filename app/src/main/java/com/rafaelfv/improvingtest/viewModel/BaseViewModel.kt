package com.rafaelfv.improvingtest.viewModel

import androidx.lifecycle.ViewModel
import com.rafaelfv.improvingtest.application.MyApplication

abstract class BaseViewModel : ViewModel() {

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MainViewModel -> MyApplication.component.inject(this)
        }
    }
}