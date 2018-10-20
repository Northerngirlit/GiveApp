package com.truecaller.giveapp.presenter

import android.content.Intent
import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.model.api.ItemRepository
import com.truecaller.giveapp.model.api.OnItemEventCallback
import com.truecaller.giveapp.view.ItemListView
import javax.inject.Inject

class ItemListPresenter @Inject constructor(
    private val itemRepository: ItemRepository
) : BasePresenter<ItemListView>(), OnItemEventCallback {

    init {
        itemRepository.itemEventEventCallback = this
    }

    override fun onItemListLoaded(itemList: List<Item>) {
        view?.showProgress(false)
        view?.showItemList(itemList)
    }

    override fun onItemAdded(item: Item) {
        view?.showProgress(false)
    }

    override fun onItemEventError(errorMessage: String) {
        view?.showProgress(false)
        view?.showError(errorMessage)
    }

    fun loadItems() {
        view?.showProgress(true)
        itemRepository.loadItems()
    }

    fun addItem() {
       view?.openAddActivity()


//        val item = Item()
//        item.title = "Cheese"
//        item.description = "Gauda cheese for free."
//        itemRepository.saveItem(item)
//        view?.showProgress(true)
    }

}