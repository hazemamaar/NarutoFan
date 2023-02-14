package com.example.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.home.databinding.ItemRelationsRvBinding
import javax.inject.Inject

@Suppress("NAME_SHADOWING")
class RelationsAdapter @Inject constructor() :
    RecyclerView.Adapter<RelationsAdapter.RelationsViewHolder>() {

    inner class RelationsViewHolder(private val binding: ItemRelationsRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String,position: Int) {
            val position=position.plus(1)
            "$position. $item".also { binding.txtRelation.text = it }
        }
    }

    var relations: List<String>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String,
        ): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RelationsAdapter.RelationsViewHolder {
        return RelationsViewHolder(
            ItemRelationsRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: RelationsAdapter.RelationsViewHolder, position: Int) {
        val relationStr = relations[position]
        holder.apply {
            bind(relationStr,position)
        }
    }

    override fun getItemCount() = relations.size
}