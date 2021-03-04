package com.rafaelfv.improvingtest

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.rafaelfv.improvingtest.ui.FragmentMain
import com.rafaelfv.improvingtest.utils.FRAGMENT_TAG_MAIN
import com.rafaelfv.improvingtest.utils.setFragment
import com.rafaelfv.improvingtest.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        this.supportFragmentManager.setFragment(
            FragmentMain(),
            R.id.content_main,
            FRAGMENT_TAG_MAIN
        )

    }
}