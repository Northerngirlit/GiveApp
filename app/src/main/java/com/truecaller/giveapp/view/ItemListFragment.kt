package com.truecaller.giveapp.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.truecaller.giveapp.App
import com.truecaller.giveapp.R
import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.presenter.ItemListPresenter
import com.truecaller.giveapp.view.adapters.ItemListAdapter
import kotlinx.android.synthetic.main.fragment_item_list.*
import javax.inject.Inject

class ItemListFragment : Fragment(), ItemListView {

    @Inject
    lateinit var presenter: ItemListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onAttachView(this)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.onDetachView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAddItem.setOnClickListener { openAddActivity() }

        presenter.loadItems()

    }

    private fun setUpRecyclerView(items: ArrayList<Item>) {
        rvItems.adapter = ItemListAdapter(items)
    }

    override fun showItemList(itemList: List<Item>) {
        setUpRecyclerView(itemList as ArrayList<Item>)
        Toast.makeText(context, "Items loaded ${itemList.size}", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun openAddActivity() {
        val intent = Intent(context, AddItemActivity::class.java)
        startActivity(intent)
    }
}