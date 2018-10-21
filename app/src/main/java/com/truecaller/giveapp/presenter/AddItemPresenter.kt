package com.truecaller.giveapp.presenter

import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.model.api.ItemRepository
import com.truecaller.giveapp.model.api.OnItemEventCallback
import com.truecaller.giveapp.view.AddItemView
import javax.inject.Inject

class AddItemPresenter @Inject constructor(
    private val itemRepository: ItemRepository
) : BasePresenter<AddItemView>(), OnItemEventCallback {
    override fun onItemListLoaded(itemList: List<Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemEventError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun saveItem(item: Item) {
        itemRepository.saveItem(item)
        view?.showProgress(true)
    }

    override fun onItemAdded(item: Item) {
        view?.showProgress(false)
    }
}