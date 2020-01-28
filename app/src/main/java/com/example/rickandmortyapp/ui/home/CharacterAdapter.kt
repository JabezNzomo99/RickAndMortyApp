package com.example.rickandmortyapp.ui.home

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.util.ColorUtil
import com.example.rickandmortyapp.util.ItemClickListener
import kotlinx.android.synthetic.main.row_character.view.*

class CharacterAdapter(private val characters:List<Character>,
                       private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    class CharacterViewHolder(itemView:View, private val context: Context):RecyclerView.ViewHolder(itemView){
        fun bind(character:Character, itemClickListener: ItemClickListener){
            itemView.textViewCharacterName.text = character.name
            itemView.textViewSpecies.text = character.species
            itemView.textViewGender.text = character.gender
            itemView.imageViewCharacterImage.load(character.image){
                crossfade(true)
                placeholder(R.drawable.ic_morty)
                transformations(CircleCropTransformation())
                diskCachePolicy(CachePolicy.ENABLED)
            }
            itemView.characterCardView.setOnClickListener {
                itemClickListener.onClick(character)
            }
            if(character.status!=null){
                if(character.status.equals("Dead")){
                    itemView.characterCardView.cardBackgroundColor.withAlpha(0.7.toInt())
                }
            }
            val characterColor = ColorUtil(context = context).getCharacterColor(character.species)
            itemView.constraintLayout.background.colorFilter = PorterDuffColorFilter(characterColor, PorterDuff.Mode.SRC_ATOP )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_character,parent,false), parent.context)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character,itemClickListener)
    }
}