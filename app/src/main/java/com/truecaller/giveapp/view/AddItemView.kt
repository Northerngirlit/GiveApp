package com.truecaller.giveapp.view

import com.google.firebase.storage.StorageReference

interface AddItemView {
    fun showProgress(show: Boolean)
    fun showError(errorMessage: String)
    fun showFileUploadProgress(progress: Int)
    fun showImage(imageStorageRef: StorageReference)
    fun finishActivity() {}
}