package com.cabudev.superheros

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter(var superHeroList:List<SuperHeroItemResponse> = emptyList()): RecyclerView.Adapter<SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun getItemCount(): Int {
        return superHeroList.size
    }

    override fun onBindViewHolder(viewHolder: SuperHeroViewHolder, position: Int) {
        val item =superHeroList[position]
        viewHolder.bind(item)
    }

    fun updateList(superHeroList:List<SuperHeroItemResponse>){
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }
}