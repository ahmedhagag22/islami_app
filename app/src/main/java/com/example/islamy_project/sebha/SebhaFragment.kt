package com.example.islamy_project.sebha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.islamy_project.constant
import com.example.islamy_project.databinding.FragmentSebhaBinding


class SebhaFragment : Fragment() {
    lateinit var viewBinding: FragmentSebhaBinding
    lateinit var iv_seb7a: ImageView
    lateinit var tv_counter: TextView
    lateinit var tv_conver: TextView

    var counter = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentSebhaBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_seb7a = viewBinding.imgBodySeb7a
        tv_counter = viewBinding.counterSeb7a
        tv_conver = viewBinding.converText

        tv_conver.text = constant.SOBHANALLAH
        tv_counter.text = "$counter"

        iv_seb7a.setOnClickListener {
            onClickSeb7aImage()

        }


    }

    fun onClickSeb7aImage() {
        iv_seb7a.rotation = iv_seb7a.rotation + 5
        counter++
        tv_counter.text = "$counter"

        if (tv_conver.text == constant.ELKETMA) {
            tv_conver.text = constant.ELKETMA
            counter = 0
            tv_counter.text = "$counter"
        }
        if (counter == 33) {
            if (tv_conver.text == constant.SOBHANALLAH)
                tv_conver.text = constant.ELHAMDOLLAH
            counter = 0
            tv_counter.text = "$counter"
        } else if (tv_conver.text == constant.ELHAMDOLLAH) {
            tv_conver.text = constant.ALLAHAKBR
            counter = 0
            tv_counter.text = "$counter"
        } else if (tv_conver.text == constant.ALLAHAKBR) {
            tv_conver.text = constant.ELKETMA
            counter = 0
            tv_counter.text = "$counter"

        }
    }
}
         