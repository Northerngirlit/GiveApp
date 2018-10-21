package com.truecaller.giveapp.presenter

import android.net.Uri
import com.truecaller.giveapp.model.*
import com.truecaller.giveapp.view.AddItemView
import javax.inject.Inject

class AddItemPresenter @Inject constructor(
    private val itemRepository: ItemRepository,
    private val fileStorage: FileStorage
) : BasePresenter<AddItemView>(), OnItemAddCallback, OnFileUploadCallback {

    var fileRef: String? = null

    fun uploadImage(uri: Uri) {
        fileStorage.uploadFile(uri, this)
    }

    fun saveItem(
        category: String,
        title: String,
        description: String,
        address: String,
        phoneNumber: String,
        email: String,
        lifetime: Long
    ) {
        val item = Item(title, description, category, address, phoneNumber, email, lifetime, fileRef)
        itemRepository.saveItem(item, this)
        view?.showProgress(true)
    }

    override fun onFileUploaded(fileRef: String) {
        this.fileRef = fileRef
        view?.showImage(fileStorage.getDownloadStorageRef(fileRef))
    }

    override fun onFileUploadProgress(progress: Double) {
        view?.showFileUploadProgress(progress.toInt())
    }

    override fun onFileUploadError(errorMessage: String) {
        view?.showError(errorMessage)
    }

    override fun onItemAdded() {
        view?.showProgress(false)
        view?.finishActivity()
    }

    override fun onItemEventError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}