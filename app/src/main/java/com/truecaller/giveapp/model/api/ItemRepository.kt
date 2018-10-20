package com.truecaller.giveapp.model.api

import com.google.firebase.database.*
import com.truecaller.giveapp.model.Item
import dagger.Lazy
import javax.inject.Inject

const val ITEMS_CHILD = "items"

class ItemRepository @Inject constructor(val callback: Lazy<OnItemEventCallback>) {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val itemDatabaseRef: DatabaseReference = database.reference.child(ITEMS_CHILD)

    fun loadItems() {
        itemDatabaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = dataSnapshot.children.mapNotNull { it.getValue(Item::class.java) }
                callback.get().onItemListLoaded(items)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.get().onItemEventError(databaseError.message)
            }
        })
    }

    fun saveItem(item: Item) {
        itemDatabaseRef.push().setValue(item)
            .addOnFailureListener { callback.get().onItemEventError("Error while adding new item") }
    }

    fun updateItem(item: Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun deleteItem(item: Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

