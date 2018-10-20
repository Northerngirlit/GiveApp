package com.truecaller.giveapp.model.api

import com.truecaller.giveapp.model.Item

interface OnItemEventCallback {
    fun onItemAdded(item: Item)

    fun onItemListLoaded(itemList: List<Item>)

    fun onItemEventError(errorMessage: String)
}