package com.example.ricknmorty.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ricknmorty.R
import com.example.ricknmorty.databinding.ActivityMainBinding
import com.example.ricknmorty.view.adapter.CharacterAdapter
import com.example.ricknmorty.viewmodel.CharactersViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var characterAdapter: CharacterAdapter
    lateinit var binding: ActivityMainBinding

    private val characterViewmodel by viewModel<CharactersViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        characterAdapter = CharacterAdapter()
        binding.rvCharacters.adapter = characterAdapter

        lifecycleScope.launch {
            val list = characterViewmodel.rickMortyCharactersList()
            characterAdapter.submitList(list)
        }

//        characterViewmodel.getCharacters().observe(this, Observer {
//
//            when (it.status) {
//                Status.ERROR -> TODO()
//                Status.LOADING -> TODO()
//                Status.SUCCESS -> {
//                    characterAdapter.submitList(it.data)
//                }
//
//            }
//
//        })

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Feature Under Development", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
