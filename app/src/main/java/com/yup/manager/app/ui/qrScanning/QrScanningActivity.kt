package com.yup.manager.app.ui.qrScanning

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QrScanningActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var scannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)
    }

    override fun onPause() {
        super.onPause()
        scannerView?.stopCamera()
    }

    override fun onResume() {
        super.onResume()
        /*camera_qr.setResultHandler(this)
        camera_qr.startCamera()*/
        scannerView?.setResultHandler(this)
        scannerView?.startCamera()

    }

    override fun handleResult(rawResult: Result?) {
        Toast.makeText(this, rawResult?.text, Toast.LENGTH_LONG).show()
    }
}
