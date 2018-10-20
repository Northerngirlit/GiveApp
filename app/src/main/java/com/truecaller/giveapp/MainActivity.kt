package com.truecaller.giveapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.truecaller.giveapp.view.ItemListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, ItemListFragment())
                .commit()
        }
    }
}
