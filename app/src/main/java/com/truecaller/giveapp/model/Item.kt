package com.truecaller.giveapp.model

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize
import java.util.concurrent.TimeUnit

@Parcelize
@IgnoreExtraProperties
class Item() : Parcelable {
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
    var creationTimestamp: Long = 0

    constructor(
        title: String,
        description: String,
        category: String,
        address: String,
        phoneNumber: String,
        email: String,
        lifetime: Long,
        creationTimeStamp: Long,
        fileRef: String?
    ) : this() {
        this.title = title
        this.description = description
        this.category = category
        this.address = address
        this.phone = phoneNumber
        this.email = email
        this.lifetime = lifetime
        this.creationTimestamp = creationTimeStamp
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
            "email" to email,
            "creationTimestamp" to creationTimestamp
        ).toMap()
    }
}
