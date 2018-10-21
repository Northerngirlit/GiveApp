package com.truecaller.giveapp.model

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

const val FILE_CHILD = "files"

@Singleton
class FileStorage @Inject constructor() {

    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
    private val fileStorageRef: StorageReference = storage.reference.child(FILE_CHILD)

    fun uploadFile(uri: Uri, callback: OnFileUploadCallback) {
        val fileRef = UUID.randomUUID().toString()
        fileStorageRef.child(fileRef).putFile(uri)
            .addOnSuccessListener { callback.onFileUploaded(fileRef) }
            .addOnProgressListener { taskSnapshot -> callback.onFileUploadProgress((100.0 * taskSnapshot.bytesTransferred) / taskSnapshot.totalByteCount) }
            .addOnFailureListener { callback.onFileUploadError("Error while uploading image") }
    }

    fun getDownloadStorageRef(fileRef: String): StorageReference {
        return fileStorageRef.child(fileRef)
    }

}

interface OnFileUploadCallback {

    fun onFileUploaded(id: String)

    fun onFileUploadProgress(progress: Double)

    fun onFileUploadError(errorMessage: String)
}