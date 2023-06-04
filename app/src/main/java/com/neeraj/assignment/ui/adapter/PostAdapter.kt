package com.neeraj.assignment.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.neeraj.assignment.R
import com.neeraj.assignment.data.room.entry.Post
import com.neeraj.assignment.databinding.ItemPostBinding

class PostAdapter(private val postList: MutableList<Post> = ArrayList()) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var listener: ItemClickListener? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(postList: List<Post>) {
        this.postList.addAll(postList)
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }


    class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.model = getItemAtPosition(position)
        holder.binding.executePendingBindings()
        holder.binding.itemContainer.setOnClickListener {
            listener?.onItemClick(getItemAtPosition(holder.adapterPosition))
        }
    }

    override fun getItemCount(): Int = postList.size

    private fun getItemAtPosition(position: Int): Post = postList[position]


    fun interface ItemClickListener {
        fun onItemClick(post: Post)
    }
}