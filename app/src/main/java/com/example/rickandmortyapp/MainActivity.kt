package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.rickandmortyapp.util.ColorUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainAcitivityViewModel:MainAcitivityViewModel by viewModel()
    private lateinit var view: View



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view = findViewById(R.id.mainActivityLinearLayout)
        fetchCharacters()
        window.statusBarColor = ColorUtil(this).covertColor(R.color.colorPrimaryDark)
    }

    fun fetchCharacters(){
        mainAcitivityViewModel.dataFetchState.observe(this, Observer {
            it.getContentIfNotHandled()?.let{dataFetchState->
                when(dataFetchState){
                    true->{
                        Snackbar.make(view,"Characters Fetched", Snackbar.LENGTH_SHORT).show()
                    }
                    false->{
                        Snackbar.make(view,"An Error Occurred", Snackbar.LENGTH_SHORT).show()

                    }
                }
            }
        })

        mainAcitivityViewModel.isFetching.observe(this, Observer {isFetching->
            when(isFetching){
                true->{
                    progress_circular.visibility = View.VISIBLE
                }
                false->{
                    progress_circular.visibility = View.GONE
                }
            }

        })

        mainAcitivityViewModel.fetchCharacters()
    }


}
