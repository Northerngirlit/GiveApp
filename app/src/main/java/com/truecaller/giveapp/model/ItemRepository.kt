package com.truecaller.giveapp.model

import com.google.firebase.database.*
import javax.inject.Inject

const val ITEMS_CHILD = "items"

class ItemRepository @Inject constructor() {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val itemDatabaseRef: DatabaseReference = database.reference.child(ITEMS_CHILD)

    fun loadItems(callback: OnItemLoadCallback) {
        itemDatabaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = dataSnapshot.children.mapNotNull { it.getValue(Item::class.java) }
                callback.onItemListLoaded(items)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onItemEventError(databaseError.message)
            }
        })
    }

    fun saveItem(item: Item, callback: OnItemAddCallback) {
        itemDatabaseRef.push().setValue(item)
            .addOnSuccessListener { callback.onItemAdded() }
            .addOnFailureListener { callback.onItemEventError("Error while adding new item") }
    }

    fun updateItem(item: Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun deleteItem(item: Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

interface OnItemLoadCallback {

    fun onItemListLoaded(itemList: List<Item>)

    fun onItemEventError(errorMessage: String)
}

interface OnItemAddCallback {

    fun onItemAdded()

    fun onItemEventError(errorMessage: String)
}

