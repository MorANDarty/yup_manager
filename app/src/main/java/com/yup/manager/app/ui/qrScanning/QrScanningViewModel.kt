package com.yup.manager.app.ui.qrScanning

import androidx.lifecycle.MutableLiveData
import com.yup.manager.app.interactors.OrdersInteractor
import com.yup.manager.app.ui.base.BaseViewModel
import com.yup.manager.data.utils.Response
import com.yup.manager.domain.entities.order.RespScanning
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//created by Ilmir Shagabiev

class QrScanningViewModel @Inject constructor(
    private val ordersInteractor: OrdersInteractor
) : BaseViewModel() {

    var scanningRespLiveData = MutableLiveData<Response<RespScanning>>()

    fun scanQr(info:String?){
        showLoadingLiveData.value = true
        disposables.add(ordersInteractor.scanQr(info)
            .doFinally { showLoadingLiveData.postValue(false)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                scanningRespLiveData.value = Response.success(it)
            },{
                scanningRespLiveData.value = Response.error(it)
            }))
    }

}