package com.truecaller.giveapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.truecaller.giveapp.App
import com.truecaller.giveapp.R
import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.presenter.AddItemPresenter
import com.truecaller.giveapp.utils.configToolbar
import kotlinx.android.synthetic.main.activity_add_item.*
import javax.inject.Inject

class AddItemActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, AddItemView {

    @Inject
    lateinit var presenter: AddItemPresenter

    var category: String = ""
    private val listOfSpinnerItems =
        arrayOf("Not sure", "Vegetables", "Fruits", "Breads", "Meat", "Juice", "Wine", "Beer", "Ice cream", "Candy")

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        configToolbar(toolbarAddItem, true, getString(R.string.action_add_item))
        setUpCategorySpinner()
        presenter.onAttachView(this)
    }

    private fun setUpCategorySpinner() {
        val spinner = findViewById<Spinner>(R.id.category_spinner)
        spinner.onItemSelectedListener = this
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listOfSpinnerItems
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        category = listOfSpinnerItems[position]
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
            itemTitle.error = getString(R.string.title_cannot_be_empty)
            return
        }

        if (description.isBlank()) {
            itemDescription.requestFocus()
            itemDescription.error = getString(R.string.description_cannot_be_empty)
            return
        }

        if (phoneNumber.isBlank()) {
            itemPhoneNumber.requestFocus()
            itemPhoneNumber.error = getString(R.string.phone_number_cannot_be_empty)
            return
        }

        val item = Item()
        item.category = category
        item.title = title
        item.description = description
        item.address = itemLocation.text.toString()
        item.phone = phoneNumber
        item.email = itemEmail.text.toString()
        item.lifetime = itemLifeTime.text.toString().toLong()
        presenter.saveItem(item)
    }

    override fun onStop() {
        presenter.onDetachView()
        super.onStop()
    }

    override fun showProgress(show: Boolean) {
        progressDialog.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun finishActivity() {
        finish()
    }
}