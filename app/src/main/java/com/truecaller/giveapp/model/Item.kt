package com.truecaller.giveapp.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.util.concurrent.TimeUnit

@IgnoreExtraProperties
class Item() {
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

    constructor(
        title: String,
        description: String,
        category: String,
        address: String,
        phoneNumber: String,
        email: String,
        lifetime: Long,
        fileRef: String?
    ) : this() {
        this.title = title
        this.description = description
        this.category = category
        this.address = address
        this.phone = phoneNumber
        this.email = email
        this.lifetime = lifetime
        this.picture = fileRef ?: ""
    }

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