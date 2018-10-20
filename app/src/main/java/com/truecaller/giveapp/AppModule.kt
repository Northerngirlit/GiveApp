package com.truecaller.giveapp

import com.truecaller.giveapp.model.api.OnItemEventCallback
import com.truecaller.giveapp.presenter.ItemListPresenter
import com.truecaller.giveapp.utils.CONTEXT_BG
import com.truecaller.giveapp.utils.CONTEXT_UI
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Dispatchers
import javax.inject.Named
import kotlin.coroutines.experimental.CoroutineContext

@Module(includes = [AppModule.Declaration::class])
class AppModule {

    @Provides
    @Named(CONTEXT_UI)
    fun uiCoroutineContext(): CoroutineContext = Dispatchers.Main

    @Provides
    @Named(CONTEXT_BG)
    fun asyncCoroutineContext(): CoroutineContext = Dispatchers.Default

    @Module
    interface Declaration {
        @Binds
        fun callback(impl: ItemListPresenter): OnItemEventCallback
    }
}