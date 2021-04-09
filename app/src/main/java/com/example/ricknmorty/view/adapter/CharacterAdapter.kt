package com.example.ricknmorty.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.ricknmorty.R
import com.example.ricknmorty.data.model.CharacterUiData
import com.example.ricknmorty.databinding.RowCharacterBinding

class CharacterAdapter :
    ListAdapter<CharacterUiData, CharacterAdapter.ViewHolder>(CharacterDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: RowCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: CharacterUiData) {

            binding.image.load("${item.imageUrl}") {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
//                transformations(CircleCropTransformation())
            }

            binding.txtEpisodesCount.text = "Episode Appearances: " + item.episodeCount
            binding.txtName.text = item.name
            binding.txtOrigin.text = item.origin
            binding.txtSpecies.text = item.species
            binding.txtStatus.text = item.status


//val photocolor = ViewUtils.createPaletteSync()
//
//            Palette.from(photo).generate { palette ->
//                val bgColor = palette?.getMutedColor(
//                    ContextCompat.getColor(binding.root.context,
//                        android.R.color.black))
//                if (bgColor != null) {
//                    binding.detailsLayout.setBackgroundColor(bgColor)
//                }
//            }


        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowCharacterBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
//
//            val loader = ImageLoader(this)
//            val req = LoadRequest.Builder(this)
//                .data("https://images.dog.ceo/breeds/saluki/n02091831_3400.jpg") // demo link
//                .target { result ->
//                    val bitmap = (result as BitmapDrawable).bitmap
//                }
//                .build()
//
//            val disposable = loader.execute(req)
        }
    }
}


class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterUiData>() {

    override fun areItemsTheSame(oldItem: CharacterUiData, newItem: CharacterUiData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterUiData, newItem: CharacterUiData): Boolean {
        return oldItem == newItem
    }

}