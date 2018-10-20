package com.truecaller.giveapp.view

import com.truecaller.giveapp.model.Item

interface ItemListView {
    fun showItemList(itemList: List<Item>)
    fun showProgress(show: Boolean)
    fun showError()
}
