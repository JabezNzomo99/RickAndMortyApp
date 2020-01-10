package com.example.rickandmortyapp.util

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.rickandmortyapp.R

class ColorUtil(private val context:Context){


    fun getCharacterColor(typeOfCharacter:String?): Int {
        val color = when (typeOfCharacter?.toLowerCase()) {
            "human" -> R.color.lightTeal
            "humanoid" -> R.color.lightRed
            "animal" -> R.color.lightBlue
            "alien","vampire" -> R.color.lightYellow
            "poopybutthole" -> R.color.lightPurple
            "mytholog" -> R.color.lightBrown
            else -> return R.color.lightBlue
        }
        return covertColor(color)
    }

    fun covertColor(color: Int): Int {
        return Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(context, color)))
    }
}