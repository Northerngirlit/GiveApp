package com.truecaller.giveapp.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.truecaller.giveapp.R
import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.utils.inflate
import com.truecaller.giveapp.utils.loadUrl
import kotlinx.android.synthetic.main.row_item.view.*


class ItemListAdapter(private val items: ArrayList<Item>) :
    RecyclerView.Adapter<ItemListAdapter.ItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(parent.inflate(R.layout.row_item))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun addItems(newItems: List<Item>) {
        val count = itemCount
        items.addAll(newItems)
        notifyItemRangeInserted(count, newItems.size)
    }

    class ItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Item) {
            itemView.title.text = item.title

            itemView.thumbnail.loadUrl(item.logo)//TODO load thumbnail
        }


    }
}