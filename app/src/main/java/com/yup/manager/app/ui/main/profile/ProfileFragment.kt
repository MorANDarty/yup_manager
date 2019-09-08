package com.yup.manager.app.ui.main.profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.yup.manager.R
import com.yup.manager.app.ManagerApplication
import com.yup.manager.app.ui.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.HttpException
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        return v
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as ManagerApplication).getAppComponent()?.inject(this)
    }

    fun initClickListeners(){
        btn_exit.setOnClickListener {

        }

        btn_help.setOnClickListener {  }

        btn_make_promocode.setOnClickListener {  }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.getProfile()
        initClickListeners()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel::class.java)
        observeDataLoading()
        observeProfile()
    }

    private fun observeProfile() {
        profileViewModel.profileLiveData.observe(this, Observer {
            if (it.data!=null){
                val profile = it.data
                tv_rating.text = profile.rating
                tv_name.text = profile.name
                tv_likes.text = profile.likes
            }
            if(it.error!=null){
                val error = it.error
                if(it is HttpException){
                    when(it.code()){
                        //Todo
                    }
                }else{
                    Toast.makeText(context, "Ошибка сети", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun observeDataLoading() {
        profileViewModel.showLoadingLiveData.observe(this, Observer{
            showLoading(it)
        })
    }

    private fun showLoading(state: Boolean?) {
        if(state==true){
            pb_profile.visibility = View.VISIBLE
        }
        if(state==false){
            pb_profile.visibility = View.INVISIBLE
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {

            }
    }
}
