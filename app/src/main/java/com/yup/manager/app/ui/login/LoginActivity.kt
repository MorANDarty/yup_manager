package com.yup.manager.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yup.manager.R
import com.yup.manager.app.ManagerApplication
import com.yup.manager.app.ui.ViewModelFactory
import com.yup.manager.app.ui.main.MainActivity
import com.yup.manager.app.utils.simpleLog
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.HttpException
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        (application as ManagerApplication).getAppComponent()?.inject(this)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginViewModel::class.java)

        loginViewModel.isLoggedIn()
        observeIsLoggedIn()
        setClickListeners()
    }

    private fun observeIsLoggedIn() {
        loginViewModel.isLoggedInLiveData.removeObservers(this)
        loginViewModel.isLoggedInLiveData.observe(this, Observer {
            simpleLog("isLoggedIn = ${it.data}")
            if (it.data == true) {
                startMain()
            }
        })
    }

    private fun setClickListeners() {
        btn_login.setOnClickListener {
            startPb()
            loginViewModel.login(et_login.text.toString(), et_password.text.toString())
            observeLogin()
        }
    }

    fun observeLogin() {
        loginViewModel.loginLiveData.removeObservers(this)
        loginViewModel.loginLiveData.observe(this, Observer {
            finishPb()
            if (it.data == true) {
                startMain()
            }
            if(it.error !=null){
                if(it.error is HttpException){
                    Toast.makeText(this, it.error.response().errorBody()?.string(), Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun startMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startPb() {
        pb_login.visibility = View.VISIBLE
    }

    private fun finishPb() {
        pb_login.visibility = View.INVISIBLE
    }
}
