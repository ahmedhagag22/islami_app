package com.example.islamy_project.radio

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.islamy_project.R
import com.example.islamy_project.api.model.ApiManger
import com.example.islamy_project.api.model.Radio
import com.example.islamy_project.api.model.RadioResponse
import com.example.islamy_project.databinding.FragmentRadioBinding
import com.example.islamy_project.player.PlayService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Connection


class RadioFragment : Fragment() {
    lateinit var viewBinding: FragmentRadioBinding
    val adapter = RadioAdapter()

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

        adapter.onItemClickPlay = object : RadioAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, item: Radio) {
                startRadioService(item)
            }

        }
        adapter.onItemClickStop = object : RadioAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, item: Radio) {
                startPlayService()
            }

        }



        getChannelsFromApi()
    }

    override fun onStart() {
        super.onStart()
        startService()
        bindService()
    }



    fun startService() {
        val intent = Intent(activity, PlayService::class.java)
        activity?.startService(intent)
    }

    fun bindService() {
        val intent = Intent(activity, PlayService::class.java)
        activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, mBinder: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = mBinder as PlayService.MyBinder
            service = binder.getService()
            bound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            bound = false
        }
    }

    private fun startRadioService(item: Radio) {
        if (bound && item.url != null && item.name != null)
            service.startMediaPlayer(item.url, item.name)


    }

    fun startPlayService() {
        if (bound)
            service.pauseMediaPlayer()
    }

    lateinit var service: PlayService

    var bound = false

    private fun getChannelsFromApi() {

        Log.e("getApi", "getApi")
        ApiManger.getWebService()
            .getRadio()
            .enqueue(object : Callback<RadioResponse> {
                override fun onResponse(
                    call: Call<RadioResponse>,
                    response: Response<RadioResponse>
                ) {
                    Log.e("response", response.body()?.radios.toString())
                    val channels = response.body()?.radios;
                    adapter.changeDate(channels ?: listOf())
                }

                override fun onFailure(call: Call<RadioResponse>, t: Throwable) {
                    Toast.makeText(activity, t.localizedMessage ?: "error", Toast.LENGTH_LONG)
                        .show()

                }

            })
    }


}
         