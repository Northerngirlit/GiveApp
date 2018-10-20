package com.truecaller.giveapp.presenter

import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.model.api.ItemRepository
import com.truecaller.giveapp.model.api.OnItemEventCallback
import com.truecaller.giveapp.view.ItemListView
import javax.inject.Inject

class ItemListPresenter @Inject constructor(
    private val itemRepository: ItemRepository
) : BasePresenter<ItemListView>(), OnItemEventCallback {

    override fun onItemListLoaded(itemList: List<Item>) {
        view?.showItemList(itemList)
    }

    override fun onItemAdded(item: Item) {
        view?.showProgress(false)
    }

    override fun onItemEventError(errorMessage: String) {
        view?.showError(errorMessage)
    }

    fun loadItems() {
        itemRepository.loadItems()
    }

    fun addItem() {
        val item = Item("Cheese")
        item.title = "Cheese"
        item.description = "Gauda cheese for free."
        itemRepository.saveItem(item)
        view?.showProgress(true)
    }

}