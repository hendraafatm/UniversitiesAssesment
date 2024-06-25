package com.universities.listing.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.universities.listing.data.model.University
import com.universities.listing.databinding.ItemUniversityBinding

class UniversityAdapter(
    private var universities: List<University>,
    private val onItemClick: (University) -> Unit
) : RecyclerView.Adapter<UniversityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val university = universities[position]
        holder.bind(university)
    }

    fun updateData(newUniversities: List<University>) {
        universities = newUniversities
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = universities.size

    inner class ViewHolder(private val binding: ItemUniversityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(university: University) {
            binding.tvUniversityName.text = university.name
            binding.tvUniversityState.text = university.state ?: ""
            binding.root.setOnClickListener { onItemClick(university) }
        }
    }
}
