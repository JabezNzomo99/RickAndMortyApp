package com.example.rickandmortyapp.ui.home.characterdetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Character
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_character_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment() {

    private val aboutFragmentViewModel : AboutFragmentViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance(id: String) = AboutFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")?.let {characterId->
            aboutFragmentViewModel.getCharacter(characterId).observe(this, Observer {character->
                textViewCreated.text = character.created
                textViewGender.text = character.gender
                textViewStatus.text = character.status
            })
        }
    }




}
