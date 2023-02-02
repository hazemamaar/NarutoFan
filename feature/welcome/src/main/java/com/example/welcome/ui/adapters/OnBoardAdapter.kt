package com.example.welcome.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.welcome.data.model.OnBoardingModel
import com.example.welcome.databinding.ItemOnboardingBinding
import javax.inject.Inject

class OnBoardAdapter @Inject constructor() : RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {

    inner class OnBoardViewHolder(private val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item:OnBoardingModel){
                binding.imgOnboard.setImageResource(item.welcomeImage)
                binding.splashtitle.setText(item.welcomeWord)
                binding.descriptionsplash.setText(item.welcomeText)
            }
    }

    var onBoardList: List<OnBoardingModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<OnBoardingModel>() {
        override fun areItemsTheSame(oldItem: OnBoardingModel, newItem: OnBoardingModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: OnBoardingModel,
            newItem: OnBoardingModel,
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        return OnBoardViewHolder(
            ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private var onItemClickListener: ((OnBoardingModel) -> Unit)? = null

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        val onBoard = onBoardList[position]
        holder.apply {
            bind(onBoard)
        }
    }

    override fun getItemCount() = onBoardList.size

    fun setOnItemClickListener(listener: (OnBoardingModel) -> Unit) {
        onItemClickListener = listener
    }
}