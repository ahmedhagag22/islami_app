package com.example.islamy_project.sebha

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.islamy_project.R
import com.example.islamy_project.constant
import com.example.islamy_project.databinding.FragmentSebhaBinding


class SebhaFragment : Fragment() {
    lateinit var img_view: ImageView
    lateinit var txtview_convert: TextView
    lateinit var txtview_counter: TextView
    lateinit var viewBinding: FragmentSebhaBinding
    var counter = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentSebhaBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtview_convert = viewBinding.tvConvertSobhanAaalh
        txtview_convert.text=constant.SOBHANALLAH
        img_view = viewBinding.imgBodySebha
        txtview_counter = viewBinding.TheNumber
        txtview_counter.text = "$counter"



        img_view.setOnClickListener(View.OnClickListener {
            OnClickSebhaImage()

        })
    }

    fun OnClickSebhaImage() {
        img_view.rotation = img_view.rotation + 5

        counter++
        txtview_counter.text = "$counter"

        if(txtview_convert.text==constant.ELKETMA)
        {
            txtview_convert.text = constant.SOBHANALLAH
            counter = 0
            txtview_counter.text = "$counter"
        }

        if (counter == 33) {
            if (txtview_convert.text == constant.SOBHANALLAH) {
                txtview_convert.text = constant.ELHAMDOLLAH
                counter = 0
                txtview_counter.text = "$counter"
            } else if( txtview_convert.text==constant.ELHAMDOLLAH)

                {
                  txtview_convert.text=constant.ALLAHAKBR
                    counter = 0
                    txtview_counter.text = "$counter"

                }
            else if (txtview_convert.text==constant.ALLAHAKBR)
            {
                txtview_convert.text=constant.ELKETMA
                counter = 0
                txtview_counter.text = "$counter"
            }

        }

    }


}
         