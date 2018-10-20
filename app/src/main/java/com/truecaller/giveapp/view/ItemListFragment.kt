package com.truecaller.giveapp.view

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
import javax.inject.Inject

class ItemListFragment: Fragment(), ItemListView {

    @Inject
    lateinit var presenter: ItemListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this);
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
        view.findViewById<View>(R.id.add).setOnClickListener { presenter.addItem() }
    }

    override fun showItemList(itemList: List<Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress(show: Boolean) {
        Toast.makeText(context, "Progress $show", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}