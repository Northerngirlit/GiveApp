package com.truecaller.giveapp.presenter

import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.model.api.ItemRepository
import com.truecaller.giveapp.utils.CONTEXT_BG
import com.truecaller.giveapp.utils.CONTEXT_UI
import dagger.Lazy
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.experimental.CoroutineContext

class ItemManager @Inject constructor(
    private val itemRepository: ItemRepository,
    @Named(CONTEXT_UI) private val uiCoroutineContext: CoroutineContext,
    @Named(CONTEXT_BG) private val bgCoroutineContext: CoroutineContext,
    private val onItemCallbackRef: Lazy<OnItemCallback>
) {

    fun loadItems() {
        launch(uiCoroutineContext) {
            val items: List<Item> = withContext(bgCoroutineContext) {
                itemRepository.getItems()
            }
            onItemCallbackRef.get().onItemsLoaded(items)
        }
    }

    fun addItem(item: Item) {
        launch(uiCoroutineContext) {
            withContext(bgCoroutineContext) {
                itemRepository.saveItem(item)
            }
            onItemCallbackRef.get().onItemAdded()
        }
    }

}

interface OnItemCallback {
    fun onItemsLoaded(items: List<Item>)

    fun onItemAdded()
}