package com.truecaller.giveapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.view.ItemDetailsFragment
import com.truecaller.giveapp.view.ItemListFragment

class MainActivity : AppCompatActivity(), ItemListFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFragment(ItemListFragment())
    }

    override fun onItemClicked(item: Item) {
        addFragment(ItemDetailsFragment.newInstance(item), true)
    }

    private fun addFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        if (addToBackStack)
            fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}
