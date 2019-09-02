package com.yup.manager.app.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


//created by Ilmir Shagabiev

open class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()
    val showLoadingLiveData = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }
}
