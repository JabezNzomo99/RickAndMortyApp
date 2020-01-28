package com.example.rickandmortyapp.ui.home.characterdetail


import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.home.ViewPagerAdapter
import com.example.rickandmortyapp.util.ColorUtil
import kotlinx.android.synthetic.main.fragment_character_detail.*
import kotlinx.android.synthetic.main.fragment_character_detail.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class CharacterDetailFragment : Fragment() {

    private val characterDetailFragmentViewModel : CharacterDetailFragmentViewModel by viewModel()
    private lateinit var character_id:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("characterId").let {characterId->
            characterId?.let {id->

                characterDetailFragmentViewModel.getCharacter(id).observe(this, Observer { character->
                    character_id = character.id
                    val color = ColorUtil(requireContext()).getCharacterColor(character.species)
                    app_bar.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.CLEAR  )
                    toolbar_layout.contentScrim?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP )
                    activity?.window?.statusBarColor = ColorUtil(requireContext()).getCharacterColor(character.species)
                    textViewName.text = character.name
                    textViewSpecies.text = character.species
                    imageViewCharacter.load(character.image){
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                    setUpViewPager()
                })
            }
        }
    }

    private fun setUpViewPager(){
        if(::character_id.isInitialized){
            val adapter = ViewPagerAdapter(requireFragmentManager())
            adapter.addFragment(AboutFragment.newInstance(character_id), "About")
            adapter.addFragment(EpisodeFragment.newInstance(character_id), "Episodes")
            viewPager.adapter = adapter
            tabs.setupWithViewPager(viewPager)
        }
    }


}
