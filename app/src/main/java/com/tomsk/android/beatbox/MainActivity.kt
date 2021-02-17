package com.tomsk.android.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tomsk.android.beatbox.databinding.ActivityMainBinding
import com.tomsk.android.beatbox.databinding.ListItemSoundBinding
private val TAG = "__MActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        beatBox= BeatBox(assets)
        //beatBox.loadSounds()

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }
    }


    private inner class SoundHolder(private val binding: ListItemSoundBinding) :
            RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = SoundViewModel()
            Log.d(TAG, "Init SoundHolder")
        }

        fun bind(sound: Sound) {

            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
            Log.d(TAG, "bind $sound")
        }

    }

    private inner class SoundAdapter(private val sounds : List<Sound>) :
            RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                    layoutInflater,
                    R.layout.list_item_sound,
                    parent,
                    false
            )

            binding.lifecycleOwner = this@MainActivity
            Log.d(TAG, "____onCreateViewHolder")
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
            Log.d(TAG, "____onBindViewHolder  bind $sound  on position  $position" )

        }

        override fun getItemCount() = sounds.size


    }
}