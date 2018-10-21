package com.truecaller.giveapp.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.truecaller.giveapp.GlideApp
import com.truecaller.giveapp.R
import com.truecaller.giveapp.model.FileStorage
import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.utils.inflate
import com.truecaller.giveapp.utils.loadUrl
import kotlinx.android.synthetic.main.row_item.view.*


class ItemListAdapter(
    private val items: ArrayList<Item>,
    private val fileStorage: FileStorage,
    private val listener: (Item) -> Unit
) :
    RecyclerView.Adapter<ItemListAdapter.ItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(parent.inflate(R.layout.row_item), fileStorage)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun addItems(newItems: List<Item>) {
        val count = itemCount
        items.addAll(newItems)
        notifyItemRangeInserted(count, newItems.size)
    }

    class ItemsViewHolder(view: View, val fileStorage: FileStorage) : RecyclerView.ViewHolder(view) {

        fun bind(item: Item, listener: (Item) -> Unit) = with(itemView) {
            title.text = item.title

            if (!item.picture.isEmpty()) {
                val context = itemView.context

                GlideApp.with(context)
                    .load(fileStorage.getDownloadStorageRef(item.picture))
                    .into(itemView.thumbnail)
            }

            setOnClickListener { listener(item) }

        }
    }
}