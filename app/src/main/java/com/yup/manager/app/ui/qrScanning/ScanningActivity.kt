package com.yup.manager.app.ui.qrScanning

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.zxing.Result
import com.yup.manager.R
import com.yup.manager.app.ManagerApplication
import com.yup.manager.app.ui.ViewModelFactory
import com.yup.manager.domain.entities.order.RespScanning
import kotlinx.android.synthetic.main.activity_test.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import javax.inject.Inject

class ScanningActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var qrViewModel: QrScanningViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        doRequestPermission()
        initScannerView()
        ll_back_qr.setOnClickListener {
            finish()
        }
        (application as ManagerApplication).getAppComponent()?.inject(this)
        qrViewModel = ViewModelProviders.of(this, viewModelFactory).get(QrScanningViewModel::class.java)
        observeLoadingData()
        observeOrderListData()
    }

    private fun observeOrderListData() {
        qrViewModel.showLoadingLiveData.observe(this, Observer {
            showLoading(it)
        })
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) pb_scanning.visibility = View.VISIBLE
        if (isLoading == false) pb_scanning.visibility = View.INVISIBLE
    }

    private fun observeLoadingData() {
        qrViewModel.scanningRespLiveData.observe(this, Observer {
            if (it.data != null) {
                showMessageWindow(it.data)
            }
            if (it.error != null) {
                Toast.makeText(this, "Some error ${it.error.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showMessageWindow(data: RespScanning) {
        Toast.makeText(this, data.details, Toast.LENGTH_LONG).show()
        Log.i("Scanning", "showMessageWindow")
        Glide.with(this).load(data.imgUrl).into(img_scanning)
        tv_time_scanning.text = data.time
        tv_name_scanning.text = data.name
        tv_event_details_scanning.text = data.details
        btn_exit_scanning.setOnClickListener {
            window_info.visibility = View.INVISIBLE
            qr_view.visibility = View.VISIBLE
            mScannerView?.resumeCameraPreview(this)
        }
        qr_view.visibility = View.INVISIBLE
        window_info.visibility = View.VISIBLE
    }

    override fun handleResult(result: Result?) {
        qrViewModel.scanQr(result?.text)
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
