package com.truecaller.giveapp.presenter

import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.model.ItemRepository
import com.truecaller.giveapp.view.ItemListView
import javax.inject.Inject

class ItemListPresenter @Inject constructor(
    private val itemRepository: ItemRepository
) : BasePresenter<ItemListView>(), OnItemLoadCallback {

    fun loadItems() {
        view?.showProgress(true)
        itemRepository.loadItems(this)
    }

    override fun onItemListLoaded(itemList: List<Item>) {
        view?.showProgress(false)
        view?.showItemList(itemList)
    }

    override fun onItemEventError(errorMessage: String) {
        view?.showProgress(false)
        view?.showError(errorMessage)
    }
}