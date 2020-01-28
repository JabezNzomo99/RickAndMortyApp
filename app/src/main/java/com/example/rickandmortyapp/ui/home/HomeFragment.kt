package com.example.rickandmortyapp.ui.home


import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.util.ColorUtil
import com.example.rickandmortyapp.util.ItemClickListener
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
        activity?.window?.statusBarColor = ColorUtil(layout.context).covertColor(R.color.colorPrimaryDark)
        setUpViewModel()
        setUpSearchView()
    }




    private fun setUpViewModel(){
        homeFragmentViewModel.getAllCharacters().observe(this, Observer {characters->
            if(!characters.isNullOrEmpty()){
                setUpRecyclerView(characters)
            }

        })

    }

    private fun setUpRecyclerView(characters:List<Character>){
        val adapter = CharacterAdapter(characters, object:ItemClickListener{
            override fun onClick(character: Character) {
                val bundle = bundleOf("characterId" to character.id)
                findNavController().navigate(R.id.action_homeFragment_to_characterDetailFragment, bundle)
            }
    })
        recyclerView.adapter = adapter
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
    }
    
    private fun setUpSearchView(){
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    homeFragmentViewModel.searchCharaters(newText).observe(this@HomeFragment, Observer {
                        setUpRecyclerView(it)
                    })
                }
                return true
            }

        })
    }




}
