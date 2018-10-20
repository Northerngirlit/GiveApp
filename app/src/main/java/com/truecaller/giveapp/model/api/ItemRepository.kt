package com.truecaller.giveapp.model.api

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.truecaller.giveapp.model.Item
import javax.inject.Inject

const val ITEMS_CHILD = "items"

class ItemRepository @Inject constructor() {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val itemDatabaseRef: DatabaseReference = database.reference

    fun getItems(): List<Item> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun saveItem(item: Item) {
        itemDatabaseRef.child(ITEMS_CHILD).push().setValue(item)
    }

    fun updateItem(item: Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun deleteItem(item: Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
