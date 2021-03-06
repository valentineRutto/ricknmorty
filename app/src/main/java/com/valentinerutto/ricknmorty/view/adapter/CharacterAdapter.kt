package com.valentinerutto.ricknmorty.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.valentinerutto.ricknmorty.R
import com.valentinerutto.ricknmorty.databinding.RowCharacterBinding
import com.valentinerutto.ricknmorty.model.data.CharacterUiData


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
            binding.txtStatus.setBackgroundColor(item.color
                    ?: R.color.design_default_color_secondary)
            binding.txtName.setTextColor(item.color ?: R.color.design_default_color_secondary)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowCharacterBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
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