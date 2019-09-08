package com.yup.manager.app.ui.qrScanning

import com.yup.manager.app.interactors.OrdersInteractor
import com.yup.manager.app.ui.base.BaseViewModel
import javax.inject.Inject

//created by Ilmir Shagabiev

class QrScanningViewModel @Inject constructor(
    private val ordersInteractor: OrdersInteractor)
    : BaseViewModel() {


}