package biz.ei6.navigationdrawer

import android.app.Service

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ServiceLieLocal  : Service() {

    val TAG = "SERVICELIE"

    inner class ServiceBinderLocal : Binder() {
        fun getService()  : ServiceLieLocal =  this@ServiceLieLocal
    }

    val binder  : IBinder = ServiceBinderLocal()

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }


    fun laFonctionQuiMinteresse()  : String {
        Log.d(TAG,"Thread du service local : ${Thread.currentThread().id}")
        Log.d(TAG,"Process du service local : ${android.os.Process.myPid()}")
        return LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

}