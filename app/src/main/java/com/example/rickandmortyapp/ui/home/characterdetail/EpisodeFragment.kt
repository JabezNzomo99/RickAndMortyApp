package com.example.rickandmortyapp.ui.home.characterdetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Episode
import com.example.rickandmortyapp.ui.home.EpisodeAdapter
import kotlinx.android.synthetic.main.episode_fragment.*
import kotlinx.android.synthetic.main.fragment_home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : Fragment() {


    private val episodeFragmentViewModel: EpisodeFragmentViewModel by viewModel()

    companion object {
        fun newInstance(characterId:String) = EpisodeFragment().apply {
            arguments = Bundle().apply {
                putString("id", characterId)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.episode_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")?.let{characterId->
            episodeFragmentViewModel.getEpisodes(characterId).observe(this, Observer {episodes->
                if(!episodes.isNullOrEmpty()){
                    setUpRecyclerView(episodes)
                }
            })
        }
    }


    fun setUpRecyclerView(episodes:List<Episode>){
        val adapter = EpisodeAdapter(episodes)
        recyclerViewEpisodes.adapter = adapter

    }

}
