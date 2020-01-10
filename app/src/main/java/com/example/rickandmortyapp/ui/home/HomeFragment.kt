package com.example.rickandmortyapp.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.util.ItemClickListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val homeFragmentViewModel:HomeFragmentViewModel by viewModel()
    private lateinit var layout:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layout =  inflater.inflate(R.layout.fragment_home_fragment, container, false)
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
    }

    private fun setUpViewModel(){
        homeFragmentViewModel.isFetching.observe(this, Observer {isFetching->
            when(isFetching){
                true->{}
                false->{}
            }
        })

        homeFragmentViewModel.dataFetchState.observe(this, Observer {dataFetchState->
            when(dataFetchState){
                true->{
                    Snackbar.make(layout,"Characters Fetched", Snackbar.LENGTH_SHORT).show()
                }
                false->{
                    Snackbar.make(layout,"An Error Occurred", Snackbar.LENGTH_SHORT).show()

                }
            }
        })

        homeFragmentViewModel.fetchCharacters()

        homeFragmentViewModel.getAllCharacters().observe(this, Observer {characters->
            if(!characters.isNullOrEmpty()){
                setUpRecyclerView(characters)
            }

        })

    }

    private fun setUpRecyclerView(characters:List<Character>){
        val adapter = CharacterAdapter(characters, object:ItemClickListener{
            override fun onClick(character: Character) {
                Toast.makeText(activity, character.name, Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.adapter = adapter
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
    }
}
