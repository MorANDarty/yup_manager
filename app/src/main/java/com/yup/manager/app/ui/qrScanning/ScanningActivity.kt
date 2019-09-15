package com.yup.manager.app.ui.qrScanning

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.Result
import com.yup.manager.R
import kotlinx.android.synthetic.main.activity_test.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanningActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        doRequestPermission()
        initScannerView()
        ll_back_qr.setOnClickListener {
            finish()
        }
    }

    override fun handleResult(result: Result?) {
        Toast.makeText(this, result?.text, Toast.LENGTH_SHORT).show()
    }

    private fun initScannerView() {
        mScannerView = ZXingScannerView(this)
        mScannerView?.setAutoFocus(true)
        mScannerView?.setResultHandler(this)
        frame_layout_camera.addView(mScannerView)
    }

    override fun onPause() {
        mScannerView?.stopCamera()
        super.onPause()
    }

    override fun onStart() {
        mScannerView?.startCamera()
        doRequestPermission()
        super.onStart()
    }

    private fun doRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            100 -> {
                initScannerView()
            }
            else -> {

            }
        }
    }
}
