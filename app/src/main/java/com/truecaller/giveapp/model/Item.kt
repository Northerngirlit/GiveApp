package com.truecaller.giveapp.model

import java.util.concurrent.TimeUnit

class Item {
    var title: String = ""
    var description: String = ""
    var picture: String = ""
    var category: String = ""
    var lifetime: Long = TimeUnit.DAYS.toMillis(1)
    var latutude: Double = 0.0
    var longitude: Double = 0.0
    var phone: String = ""
    var address: String = ""
    var email: String = ""
}