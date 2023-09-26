package com.example.islamy_project.radio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.islamy_project.api.model.ApiManger
import com.example.islamy_project.api.model.RadioResponse
import com.example.islamy_project.api.model.RadioResponseItem
import com.example.islamy_project.databinding.FragmentRadioBinding
import com.example.islamy_project.player.PlayService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RadioFragment : Fragment() {
    val adapter = RadioAdapter()

    lateinit var viewBinding: FragmentRadioBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentRadioBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.radioRv.adapter = adapter

        adapter.onItemClickPlay = object : OnItemClickListener {
            override fun onItemClick(position: Int, item: RadioResponseItem) {
                startRadioService(item)
            }
        }
        adapter.onItemClickStop=object :OnItemClickListener{
            override fun onItemClick(position: Int, item: RadioResponseItem) {
                stopPlayerService()
            }

        }

        //call api
        getChannelsFromApi()

    }

    override fun onStart() {
        super.onStart()
        startService();
        bindService()
    }

    private fun startService() {
        val intent = Intent(activity, PlayService::class.java)
        activity?.startService(intent)
    }

    override fun onStop() {
        super.onStop()
      //  activity?.unbindService(connection)
    }

    fun bindService() {
        val intent = Intent(activity, PlayService::class.java)
        activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun startRadioService(item: RadioResponseItem) {
        if (bound)
            service.startMediaPlayer(item.radioUrl!!, item.name!!);
        /*
                val intent = Intent(activity,PlayerService::class.java)
                intent.putExtra("url",item)
                activity?.startService(intent);
        */
    }

    fun stopPlayerService() {
        if (bound)
            service.pauseMediaPlayer()
    }

    lateinit var service: PlayService;
    var bound = false;

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, mBinder: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = mBinder as PlayerService.MyBinder
            service = mBinder.getService()
            bound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            bound = false
        }
    }

    private fun getChannelsFromApi() {

        //show loading dialog
    ApiManger.getWebService()
        .getRadio()
        .enqueue(object :Callback<RadioResponse>{
            override fun onResponse(call: Call<RadioResponse>, response: Response<RadioResponse>) {
               val channels=response.body()?.radios
                adapter.changeData(channels)
            }

            override fun onFailure(call: Call<RadioResponse>, t: Throwable) {
                Toast.makeText(activity, t.localizedMessage ?: "error", Toast.LENGTH_LONG)
                    .show()
            }

        })
    }


}
         