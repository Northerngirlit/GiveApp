package com.truecaller.giveapp

import com.truecaller.giveapp.utils.CONTEXT_BG
import com.truecaller.giveapp.utils.CONTEXT_UI
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Dispatchers
import javax.inject.Named
import kotlin.coroutines.experimental.CoroutineContext

@Module
class AppModule {

    @Provides
    @Named(CONTEXT_UI)
    fun uiCoroutineContext(): CoroutineContext = Dispatchers.Main

    @Provides
    @Named(CONTEXT_BG)
    fun asyncCoroutineContext(): CoroutineContext = Dispatchers.Default
}