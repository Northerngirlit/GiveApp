package com.truecaller.giveapp.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.storage.StorageReference
import com.truecaller.giveapp.App
import com.truecaller.giveapp.GlideApp
import com.truecaller.giveapp.R
import com.truecaller.giveapp.presenter.AddItemPresenter
import kotlinx.android.synthetic.main.activity_add_item.*
import javax.inject.Inject

const val REQUEST_CODE_PHOTO_PICKER = 1

class AddItemActivity : AppCompatActivity(), AddItemView {

    @Inject
    lateinit var presenter: AddItemPresenter

    private val listOfSpinnerItems =
        arrayOf("Not sure", "Vegetables", "Fruits", "Breads", "Meat", "Juice", "Wine", "Beer", "Ice cream", "Candy")

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_item)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfSpinnerItems)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = arrayAdapter

        camera.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/jpeg"
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), REQUEST_CODE_PHOTO_PICKER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_PHOTO_PICKER && resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data?.data
            uri?.let {
                presenter.uploadImage(uri)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        presenter.onAttachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.onDetachView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_save -> {
                trySaveItem()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun trySaveItem() {
        val title = itemTitle.text.toString()
        val description = itemDescription.text.toString()
        val phoneNumber = itemPhoneNumber.text.toString()

        if (title.isBlank()) {
            itemTitle.requestFocus()
            itemTitle.error = "Title cannot be empty!"
            return
        }

        if (description.isBlank()) {
            itemDescription.requestFocus()
            itemDescription.error = "Description cannot be empty!"
            return
        }

        if (phoneNumber.isBlank()) {
            itemPhoneNumber.requestFocus()
            itemPhoneNumber.error = "Phone number cannot be empty!"
            return
        }

        val category = listOfSpinnerItems[categorySpinner.selectedItemPosition]
        val address = itemLocation.text.toString()
        val email = itemEmail.text.toString()
        val lifetime = itemLifeTime.text.toString().toLong()
        presenter.saveItem(category, title, description, address, phoneNumber, email, lifetime)
    }

    override fun showProgress(show: Boolean) {
        progressDialog.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showFileUploadProgress(progress: Int) {
        Toast.makeText(this, "Uploading in progress: $progress", Toast.LENGTH_SHORT).show()
    }

    override fun showImage(imageStorageRef: StorageReference) {
        GlideApp.with(this)
            .load(imageStorageRef)
            .into(logo)
    }

    override fun finishActivity() {
        finish()
    }
}