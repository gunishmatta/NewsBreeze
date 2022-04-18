package com.gunish.newsbreeze.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.gunish.newsbreeze.NewsBreezeApp
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {
    val APIKEY = "0ff7003c58b94e60a09df00075308191"
    fun currentDate() :String{
        val current = Calendar.getInstance()
        try {
            val outputFormat = SimpleDateFormat("yyyy-MM-dd")
            return outputFormat.format(current.time)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }


    fun isConnected():Boolean{
        var isConnectedWithWifi=false
        var isConnectedWithMobile=false


        var connectionManager=NewsBreezeApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(connectionManager!=null)
        {
            if(Build.VERSION.SDK_INT<23)
            {
                val networkInfo=connectionManager.activeNetworkInfo
                if(networkInfo!=null)
                {
                    return (networkInfo.isConnected&&(networkInfo.type==ConnectivityManager.TYPE_WIFI)||(networkInfo.type==ConnectivityManager.TYPE_MOBILE))
                }
            }
            else{
                val networkInfo=connectionManager.activeNetwork
                if(networkInfo!=null)
                {
                    val networkCapabilities=connectionManager.getNetworkCapabilities(networkInfo)
                    return (networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)||networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                }
            }
        }
        return isConnectedWithMobile||isConnectedWithWifi

    }

}