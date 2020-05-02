package biz.ei6.navigationdrawer.ui.home

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import biz.ei6.navigationdrawer.R
import biz.ei6.navigationdrawer.ServiceLieLocal
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    val TAG="SERVICE"
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        root.home_servicelie.setOnClickListener { lanceServiceLocal() }
        root.home_servicelieappel.setOnClickListener { appelServiceLocal() }

        root.home_serviceautreprocessus.setOnClickListener { lanceServiceDistant() }

        return root
    }

    // Pour un service lié local
    var serviceLieLocal : ServiceLieLocal? = null
    var estLie : Boolean = false

    val maConnexionLocale = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
           estLie = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
           val binder = service as ServiceLieLocal.ServiceBinderLocal
           serviceLieLocal = binder.getService()
            Log.d(TAG,"le service  est démarré")
           estLie = true
        }

    }

    private fun lanceServiceLocal() {
        val intent = Intent(requireContext(), ServiceLieLocal::class.java)
        requireActivity().bindService(intent, maConnexionLocale, Context.BIND_AUTO_CREATE)
    }

    private fun appelServiceLocal() {
        Log.d(TAG,"Thread du home : ${Thread.currentThread().id}")
        serviceLieLocal?.laFonctionQuiMinteresse()
    }

    private fun lanceServiceDistant() {

    }

}
