package com.truecaller.giveapp.presenter

import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.model.ItemRepository
import com.truecaller.giveapp.view.AddItemView
import javax.inject.Inject

class AddItemPresenter @Inject constructor(
    private val itemRepository: ItemRepository
) : BasePresenter<AddItemView>(), OnItemAddCallback {
    override fun onItemAdded() {
        view?.showProgress(false)
    }

    override fun onItemEventError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun saveItem(item: Item) {
        itemRepository.saveItem(item, this)
        view?.showProgress(true)
    }
}