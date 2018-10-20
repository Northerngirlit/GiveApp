package com.truecaller.giveapp.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.util.concurrent.TimeUnit

@IgnoreExtraProperties
class Item {
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var logo: String = ""
    var category: String = ""
    var lifetime: Long = TimeUnit.DAYS.toMillis(1)
    var latutude: Double = 0.0
    var longitude: Double = 0.0
    var phone: String = ""
    var address: String = ""

    constructor(name: String) { //Temporary constructor for testing purposes TODO remove
        title = name
    }

    @Exclude
    fun toMap(): Map<String, Any> {
        return hashMapOf(
            "title" to title,
            "description" to description,
            "logo" to logo,
            "category" to category,
            "lifetime" to lifetime,
            "latutude" to latutude,
            "longitude" to longitude,
            "phone" to phone,
            "address" to address
        ).toMap()
    }
}