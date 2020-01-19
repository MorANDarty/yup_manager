package com.yup.manager.data

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.yup.manager.app.ui.login.LoginActivity
import com.yup.manager.app.ui.main.MainActivity
import java.util.HashMap

//created by Ilmir Shagabiev

class SessionManager(private var _context: Context) {

    private var udata: SharedPreferences
    private var editData: SharedPreferences.Editor

    companion object {

        private val MANAGER_NAME = "name"
        private val MANAGER_LNAME = "lname"
        private val AUTH_TOKEN = "token"
        //File name
        private val PREF_NAME = "manager_data"
        private val PREF_MODE = 0
        //Status login/not
        private val IS_LOGIN = "isLoggedIn"
        private val PHONE_NUMBER = "phone_number"
        private val BUSINESS_PARTNER = "business_partner"
    }

    init {
        udata = _context.getSharedPreferences(PREF_NAME, PREF_MODE)
        editData = udata.edit()
        editData.apply()
    }

    fun createSession(name: String?, lastName: String?, phone: String?, authToken: String?) {
        editData.putString(MANAGER_NAME, name)
        editData.putString(MANAGER_LNAME, lastName)
        editData.putString(AUTH_TOKEN, authToken)
        editData.putBoolean(IS_LOGIN, true) //logged in
        editData.putString(PHONE_NUMBER, phone)
        editData.commit()
    }

    fun putServiceCode(code: String) {
        editData.commit()
    }

    fun putBusinessPartner(bp:String?){
        editData.putString(BUSINESS_PARTNER, "Инженер от $bp")
        editData.commit()
    }

    fun putNameAndLastName(name: String?, lName:String?){
        editData.putString(MANAGER_NAME, name)
        editData.putString(MANAGER_LNAME, lName)
        editData.commit()
    }

    fun getName() =  udata.getString(MANAGER_NAME, "")

    fun getLastName() = udata.getString(MANAGER_LNAME, "")

    fun editUser(token: String, name: String, lastName: String, engId: Int) {
        editData.putString(MANAGER_NAME, name)
        editData.putString(MANAGER_LNAME, lastName)
        editData.putString(AUTH_TOKEN, token)
        editData.commit()
    }

    fun startMain() {
        val intent = Intent(_context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        _context.startActivity(intent)

    }

    fun startLogin() {
        val intent = Intent(_context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        _context.startActivity(intent)
    }

    fun getUserDetails(): HashMap<String, String?> {
        val user = HashMap<String, String?>()

        user[MANAGER_NAME] = udata.getString(MANAGER_NAME, null)
        user[MANAGER_LNAME] = udata.getString(MANAGER_LNAME, null)
        user[AUTH_TOKEN] = udata.getString(AUTH_TOKEN, null)
        return user
    }

    fun checkLogin() {
        if (!isLoggedIn()) {
            startLogin()
        }
    }

    fun isLoggedIn(): Boolean {
        return udata.getBoolean(IS_LOGIN, false)
    }

    fun logoutUser() {
        editData.clear()
        editData.putBoolean(IS_LOGIN, false)
        editData.commit()
        /*startLogin()*/
    }

    fun getToken(): String? {
        return if (isLoggedIn()) udata.getString(AUTH_TOKEN, "") else null
    }

    fun getPhoneNumber(): String? {
        return udata.getString(PHONE_NUMBER, "")
    }

    fun getBusinessPartner():String?{
        return udata.getString(BUSINESS_PARTNER, "")
    }

}