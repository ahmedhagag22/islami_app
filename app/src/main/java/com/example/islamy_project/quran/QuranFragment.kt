package com.example.islamy_project.quran

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islamy_project.QuraanContantActivity
import com.example.islamy_project.constant
import com.example.islamy_project.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {
    lateinit var ViewBinding: FragmentQuranBinding


    lateinit var adapter: CustomAdapterQuran

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ViewBinding = FragmentQuranBinding.inflate(inflater, container, false)
        return ViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        adapter = CustomAdapterQuran(listsura, listnumberAya2)
        adapter.OnSuraClickListner = object : CustomAdapterQuran.OnItemClickListner {
            override fun OnItemClick(pos: Int, SuraName: String) {
                var Intent = Intent(activity, QuraanContantActivity::class.java)
                // TODO:send position -> to read the file ane add (+) 1
                Intent.putExtra(constant.EXTRA_SURA_POSITION, pos)

                Intent.putExtra(constant.EXTRA_SURA_Name, SuraName)

                startActivity(Intent)
            }

        }
        ViewBinding.recyclerview.adapter = adapter
    }


}
