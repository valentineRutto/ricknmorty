package com.example.ricknmorty.view

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ricknmorty.R
import com.example.ricknmorty.databinding.ActivityMainBinding
import com.example.ricknmorty.model.data.CharacterUiData
import com.example.ricknmorty.utils.NetworkResult
import com.example.ricknmorty.utils.ViewUtils
import com.example.ricknmorty.utils.ViewUtils.toBitmap
import com.example.ricknmorty.view.adapter.CharacterAdapter
import com.example.ricknmorty.viewmodel.CharactersViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URL

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
            val progresDialog = getAlertDialog(this@MainActivity, true)
            progresDialog.show()

            when (val results = characterViewmodel.getCharacterSafeApiCall()) {
                is NetworkResult.Loading -> {
                    progresDialog.show()

                }
                is NetworkResult.NetworkError -> {

                    Toast.makeText(this@MainActivity,
                            "Error", Toast.LENGTH_LONG).show()

                }
                is NetworkResult.Success -> {
                    var color: Int? = null
                    val character = results.data?.results?.map {

                        character ->
                        val url = URL("${character?.image}")

                        val result: Deferred<Bitmap?> = GlobalScope.async {
                            url.toBitmap()
                        }
                        val vibrantSwatch = result.await()?.let { ViewUtils.createPaletteSync(it).vibrantSwatch?.rgb }
                        if (vibrantSwatch != null) {
                            color = vibrantSwatch
                        }

                        CharacterUiData(
                                character?.id.toString(),
                                character?.name,
                                character?.status,
                                character?.species,
                                character?.image,
                                character?.episode?.size.toString(),
                                character?.origin?.name,
                                color
                        )
                    }
                    characterAdapter.submitList(character)
                    progresDialog.dismiss()


                }
                is NetworkResult.ServerError -> {
                    Toast.makeText(this@MainActivity, results.errorBody?.message, Toast.LENGTH_LONG).show()
                }
            }
        }


//        lifecycleScope.launch {
//            val list = characterViewmodel.rickMortyCharactersList()
//            characterAdapter.submitList(list)
//        }


//        characterViewmodel.getCharacters().observe(this, Observer {
//
//            when (it.status) {
//                Status.ERROR -> {
//                    getAlertDialog(this,false)
//             Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
//                }
//                Status.LOADING -> {
//        }
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

    fun getAlertDialog(
            context: Context,
            setCancellationOnTouchOutside: Boolean
    ): AlertDialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val customLayout: View =
                layoutInflater.inflate(R.layout.progress_dialog, null)
        builder.setView(customLayout)
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(setCancellationOnTouchOutside)
        return dialog
    }

}
