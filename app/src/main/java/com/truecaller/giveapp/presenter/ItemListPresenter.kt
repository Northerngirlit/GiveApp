package com.truecaller.giveapp.presenter

import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.view.ItemListView
import javax.inject.Inject

class ItemListPresenter @Inject constructor(
    private val itemManager: ItemManager
) : BasePresenter<ItemListView>(), OnItemCallback {

    fun loadItems() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemAdded() {
        view?.showProgress(false)
    }

    override fun onItemsLoaded(items: List<Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun addItem() {
        val item = Item()
        item.title = "Cheese"
        item.description = "Gauda cheese for free."
        itemManager.addItem(item)
        view?.showProgress(true)
    }

}