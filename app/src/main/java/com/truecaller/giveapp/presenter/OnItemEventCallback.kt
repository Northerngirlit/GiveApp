package com.truecaller.giveapp.presenter

import com.truecaller.giveapp.model.Item

interface OnItemLoadCallback {

    fun onItemListLoaded(itemList: List<Item>)

    fun onItemEventError(errorMessage: String)
}

interface OnItemAddCallback {

    fun onItemAdded()

    fun onItemEventError(errorMessage: String)
}