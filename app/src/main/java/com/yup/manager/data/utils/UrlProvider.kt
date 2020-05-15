package com.yup.manager.data.utils

//created by Ilmir Shagabiev

class UrlProvider {
    fun getDevServer(): String {
        return  "http://ec2-18-217-215-242.us-east-2.compute.amazonaws.com:3000/"
    }

    fun getProductionServer():String{
        return ""
    }
}