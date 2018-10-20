package com.truecaller.giveapp.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.util.concurrent.TimeUnit

@IgnoreExtraProperties
class Item {
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var picture: String = ""
    var category: String = ""
    var lifetime: Long = TimeUnit.DAYS.toMillis(1)
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var phone: String = ""
    var address: String = ""
    var email: String = ""

    @Exclude
    fun toMap(): Map<String, Any> {
        return hashMapOf(
            "title" to title,
            "description" to description,
            "picture" to picture,
            "category" to category,
            "lifetime" to lifetime,
            "latitude" to latitude,
            "longitude" to longitude,
            "phone" to phone,
            "address" to address,
            "email" to email
        ).toMap()
    }
}