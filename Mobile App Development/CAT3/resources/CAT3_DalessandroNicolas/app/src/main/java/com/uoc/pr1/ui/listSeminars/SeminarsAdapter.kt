package com.uoc.pr1.ui.list

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uoc.pr1.R
import com.uoc.pr1.data.model.Seminary


class SeminarsAdapter(private val onClick: (Seminary) -> Unit) :
    ListAdapter<Seminary, SeminarsAdapter.ItemViewHolder>(SeminaryDiffCallback) {

    /* ViewHolder for Item, takes in the inflated view and the onClick behavior. */
    class ItemViewHolder(itemView: View, val onClick: (Seminary) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        public var currentItem: Seminary? = null
        public var itemTextView: TextView? = null
        public var itemImageView: ImageView? = null


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
            //BEGIN-CODE-UOC-5.1

            // We check if the image path exists
            if (!item.image_path.isNullOrEmpty()) {
                // We load the bitmap from the file system
                val bitmap: Bitmap? = BitmapFactory.decodeFile(item.image_path)
                // We display the image
                bitmap?.let {
                    itemImageView?.setImageBitmap(it)
                }
            }

            //END-CODE-UOC-5.1

        }
    }


    override fun getItemViewType(position: Int): Int {

            return 1

    }

    /* Creates and inflates view and return Item. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_seminary, parent, false)
        return ItemViewHolder(view, onClick)
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