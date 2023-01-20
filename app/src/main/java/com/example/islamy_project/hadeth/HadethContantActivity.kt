package com.example.islamy_project.hadeth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.islamy_project.R
import com.example.islamy_project.constant

class HadethContantActivity : AppCompatActivity()
{
    var HadethName:String?=null
    var HadethPosition:Int?=null

    lateinit var imagbak:ImageView
    lateinit var titleHadeth:TextView
    lateinit var ContantHadeth:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hadeth_contant)
        HadethPosition=intent.getIntExtra(constant.EXTRA_HADETH_POSITION,-1)
        HadethName=intent.getStringExtra(constant.EXTRA_HADETH_NAME)

        ContantHadeth=findViewById(R.id.ContentHadeth)

        titleHadeth=findViewById(R.id.Hadeth_Name)
        titleHadeth.text=HadethName


        imagbak=findViewById(R.id.back)
        imagbak.setOnClickListener(View.OnClickListener { finish() })

        readFileText()
    }

    fun readFileText() {
        val fileName = "ahadeth.txt"
        var FileContent = assets.open(fileName).bufferedReader().use { it.readText() }

        var SuraContent= FileContent.split('#')
        ContantHadeth.text= SuraContent.toString()

}
}