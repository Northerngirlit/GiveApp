package com.truecaller.giveapp.presenter

import android.support.annotation.CallSuper

interface Presenter<in V> {

    fun onAttachView(presenterView: V)

    fun onDetachView()
}

abstract class BasePresenter<V> : Presenter<V> {

    @JvmField
    protected var view: V? = null

    @CallSuper
    override fun onAttachView(v: V) {
        view = v
    }

    @CallSuper
    override fun onDetachView() {
        view = null
    }
}