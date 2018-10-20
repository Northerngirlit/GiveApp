package com.truecaller.giveapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class AddActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var category: String = ""
    private val listOfSpinnerItems =
        arrayOf("Not sure","Vegetables", "Fruits", "Breads", "Meat", "Juice", "Wine", "Beer", "Ice cream", "Candy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setUpCategorySpinner()
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
}