package biz.ei6.navigationdrawer

import android.app.Service

import android.content.Intent
import android.os.*
import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ServiceLieDistant  : Service() {

    val TAG = "SERVICELIE"

  inner class MonHandler  : Handler() {
      override fun handleMessage(msg: Message) {
          super.handleMessage(msg)
          val data = msg.data.getString("param")
          Log.d(TAG,"Data du service distant : $data")
          Log.d(TAG,"Thread du service distant: ${Thread.currentThread().id}")
          Log.d(TAG,"Process du service distant : ${android.os.Process.myPid()}")
      }
  }

    val messenger  = Messenger(MonHandler())

    override fun onBind(intent: Intent?): IBinder? {
        return messenger.binder
    }

}