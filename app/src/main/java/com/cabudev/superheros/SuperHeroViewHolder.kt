package com.cabudev.superheros

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cabudev.superheros.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResponse:SuperHeroItemResponse, onItemSelected: (String) -> Unit){
        binding.tvSuperHeroName.text = superHeroItemResponse.name
        Picasso.get().load(superHeroItemResponse.image.url).into(binding.ivSuperHeroImage)

        binding.root.setOnClickListener {
            onItemSelected(superHeroItemResponse.superheroid)
        }
    }
}