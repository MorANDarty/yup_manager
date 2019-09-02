package com.yup.manager.app.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.yup.manager.app.ui.main.MainActivity


//created by Ilmir Shagabiev

open class BaseFragment : Fragment() {
    lateinit var rootActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        rootActivity = activity as MainActivity
    }

    fun showSnackbar(message: String) {
        view?.let { it1 ->
            Snackbar.make(
                it1,
                message,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
