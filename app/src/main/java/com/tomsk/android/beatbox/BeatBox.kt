package com.tomsk.android.beatbox

import android.content.res.AssetManager
import android.util.Log

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"

class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>
    init {
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound> {

        val soundNames: Array<String>

        try{
//            val soundNames = assets.list(SOUNDS_FOLDER)!!
//            Log.d(TAG, "Found ${soundNames.size} sounds")
//            return soundNames.asList()
            soundNames = assets.list(SOUNDS_FOLDER)!!
        }
        catch(e: Exception){
            Log.e(TAG, "Could not list assets", e)
            return emptyList()
        }

        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            sounds.add(sound)
        }
        return sounds

    }

}