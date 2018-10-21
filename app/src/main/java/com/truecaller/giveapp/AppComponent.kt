package com.truecaller.giveapp

import com.truecaller.giveapp.view.AddItemActivity
import com.truecaller.giveapp.view.ItemListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: ItemListFragment)
    fun inject(activity: AddItemActivity)
}