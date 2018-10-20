package com.truecaller.giveapp.model.api

import com.google.firebase.database.*
import com.truecaller.giveapp.model.Item
import javax.inject.Inject

const val ITEMS_CHILD = "items"

class ItemRepository @Inject constructor() {

    var itemEventEventCallback: OnItemEventCallback? = null

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val itemDatabaseRef: DatabaseReference = database.reference.child(ITEMS_CHILD)

    fun loadItems() {
        itemDatabaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = dataSnapshot.children.mapNotNull { it.getValue(Item::class.java) }
                itemEventEventCallback?.onItemListLoaded(items)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                itemEventEventCallback?.onItemEventError(databaseError.message)
            }
        })
    }

    fun saveItem(item: Item) {
        itemDatabaseRef.child(ITEMS_CHILD).push().setValue(item)
            .addOnFailureListener { itemEventEventCallback?.onItemEventError("Error while adding new item") }
    }

    fun updateItem(item: Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun deleteItem(item: Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

interface OnItemEventCallback {
    fun onItemAdded(item: Item)

    fun onItemListLoaded(itemList: List<Item>)

    fun onItemEventError(errorMessage: String)
}
