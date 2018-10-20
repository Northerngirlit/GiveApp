package com.truecaller.giveapp

import com.truecaller.giveapp.presenter.ItemListPresenter
import com.truecaller.giveapp.presenter.OnItemCallback
import com.truecaller.giveapp.utils.CONTEXT_BG
import com.truecaller.giveapp.utils.CONTEXT_UI
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Dispatchers
import javax.inject.Named
import kotlin.coroutines.experimental.CoroutineContext

@Module(includes = [AppModule.Declarations::class])
class AppModule {

    @Provides
    @Named(CONTEXT_UI)
    fun uiCoroutineContext(): CoroutineContext = Dispatchers.Main

    @Provides
    @Named(CONTEXT_BG)
    fun asyncCoroutineContext(): CoroutineContext = Dispatchers.Default

    @Module
    interface Declarations {
        @Binds
        fun onItemCallback(impl: ItemListPresenter): OnItemCallback
    }

}