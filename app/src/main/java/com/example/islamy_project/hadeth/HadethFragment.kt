package com.example.islamy_project.hadeth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islamy_project.constant
import com.example.islamy_project.databinding.FragmentHadethBinding
import kotlin.math.log


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

       // readfile()

        adapter = CustomAdapterHadeth(listhadeth)

        adapter.OnHadethClickListner = object : CustomAdapterHadeth.OnItemClickListner {
            override fun OnItemClick(position: Int, items: String) {

                var Intent = Intent(activity, HadethContantActivity::class.java)

                Intent.putExtra(constant.EXTRA_HADETH_POSITION,position)
                Intent.putExtra(constant.EXTRA_HADETH_NAME, items)

                startActivity(Intent)
            }
        }


        ViewBinding.recyclerview.adapter = adapter
    }

        val allAhadeth= mutableListOf<DataClassHadeth>()
    fun readfile()
    {

        var fileName="ahadeth.txt"
        var filcontant=activity?.assets?.open(fileName)?.bufferedReader().use {
        it?.readText()
        }
        if (filcontant==null) return
         val ahadethContant=filcontant.trim().split("#")
        ahadethContant.forEach{singleHadethContent->

            val title =singleHadethContent.trim().substringBefore('\n')
            val content =singleHadethContent.trim().substringAfter('\n')

            Log.e("title",title)
            Log.e("content",content)
            val hadeth=DataClassHadeth(title, content)
            allAhadeth.add(hadeth)


        }

    }



}
         