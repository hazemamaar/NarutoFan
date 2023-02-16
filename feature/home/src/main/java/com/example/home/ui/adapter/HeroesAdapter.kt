package com.example.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.home.common.uitils.Constant
import com.example.home.data.Hero
import com.example.home.databinding.ItemNarutoRvBinding
import javax.inject.Inject


class HeroesAdapter @Inject constructor(val glide: RequestManager) :
    RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    inner class HeroesViewHolder(private val binding: ItemNarutoRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hero) {
            binding.titleTxt.text = item.name
            binding.desTxt.text = item.about
            glide.load("${Constant.BASE_URL}${item.image}")
                .into(binding.imgHero)
           binding.cardContainer.setOnClickListener {
               onItemClickListener?.let { it(item) }
           }
        }
    }

    var heroes: List<Hero>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<Hero>() {
        override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: Hero,
            newItem: Hero,
        ): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(
            ItemNarutoRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private var onItemClickListener: ((Hero) -> Unit)? = null

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = heroes[position]
        holder.apply {
            bind(hero)
        }
    }

    override fun getItemCount() = heroes.size

    fun setOnItemClickListener(listener: (Hero) -> Unit) {
        onItemClickListener = listener
    }
}