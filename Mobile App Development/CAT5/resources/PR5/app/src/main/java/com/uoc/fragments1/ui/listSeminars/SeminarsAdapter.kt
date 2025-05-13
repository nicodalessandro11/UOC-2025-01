package com.uoc.fragments1.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uoc.fragments1.R
import com.uoc.fragments1.data.model.Seminary


class SeminarsAdapter(private val onClick: (Seminary) -> Unit) :
    ListAdapter<Seminary, SeminarsAdapter.ItemViewHolder>(SeminaryDiffCallback) {

    public lateinit var context: Context


    /* ViewHolder for Item, takes in the inflated view and the onClick behavior. */
    class ItemViewHolder(itemView: View, val onClick: (Seminary) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        public var currentItem: Seminary? = null
        public var itemTextView: TextView? = null
        public var itemImageView: ImageView? = null

        public lateinit var context: Context

        init {
            itemTextView = itemView.findViewById(R.id.item_text)
            itemImageView = itemView.findViewById(R.id.item_image)

            itemView.setOnClickListener {
                currentItem?.let {
                    it.view = itemImageView
                    onClick(it)
                }
            }
        }



        /* Bind item name and image. */
        fun bind(item: Seminary) {
            currentItem = item





            itemTextView!!.text = item.name
            if (item.image != null) {
                itemImageView!!.setImageResource(item.image)
            } else {
                itemImageView!!.setImageResource(R.drawable.ailogo)
            }





        }
    }


    override fun getItemViewType(position: Int): Int {

            return 1

    }

    /* Creates and inflates view and return Item. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_seminary, parent, false)
        val viewHolder = ItemViewHolder(view, onClick)


        viewHolder.context = context

        return viewHolder
    }

    /* Gets current Item and uses it to bind view. */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemImageView!!.transitionName = item.name
        holder.bind(item)


    }


}

object SeminaryDiffCallback : DiffUtil.ItemCallback<Seminary>() {
    override fun areItemsTheSame(oldItem: Seminary, newItem: Seminary): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Seminary, newItem: Seminary): Boolean {
        return oldItem.id == newItem.id
    }
}