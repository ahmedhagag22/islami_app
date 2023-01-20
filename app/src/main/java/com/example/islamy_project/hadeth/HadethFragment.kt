package com.example.islamy_project.hadeth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islamy_project.constant
import com.example.islamy_project.databinding.FragmentHadethBinding


class HadethFragment : Fragment() {
    lateinit var ViewBinding: FragmentHadethBinding
    lateinit var adapter: CustomAdapterHadeth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ViewBinding = FragmentHadethBinding.inflate(inflater, container, false)
        return ViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = ArrayList<DataClassHadeth>()
        for (i in 1..50) {
            data.add(DataClassHadeth("الحديث رقم " + "$i"))
        }

        adapter = CustomAdapterHadeth(data)

        adapter.OnHadethClickListner = object : CustomAdapterHadeth.OnItemClickListner {
            override fun OnItemClick(position: Int, items: DataClassHadeth) {

                var Intent = Intent(activity, HadethContantActivity::class.java)

                Intent.putExtra(constant.EXTRA_HADETH_POSITION,position)
                Intent.putExtra(constant.EXTRA_HADETH_NAME, "$items")

                startActivity(Intent)
            }
        }


        ViewBinding.recyclerview.adapter = adapter
    }


}
         