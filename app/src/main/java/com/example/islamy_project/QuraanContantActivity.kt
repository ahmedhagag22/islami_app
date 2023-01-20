package com.example.islamy_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class QuraanContantActivity : AppCompatActivity() {
    lateinit var imgback: ImageView

    lateinit var tv_sura_content: TextView

    lateinit var tv_sura_name: TextView

    // TODO: receive name ,position from the quranFragment   
    var SuraName: String? = null
    var SuraPosition: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activty_quraan_contant)
        imgback = findViewById(R.id.back)
        tv_sura_content = findViewById(R.id.ContentSura)
        tv_sura_name = findViewById(R.id.sura_name)
        // TODO: receive 
        SuraName = intent.getStringExtra(constant.EXTRA_SURA_Name)
        // TODO: استخدمنا المكان عشات نقدر نفتح الملف بيه  
        SuraPosition = intent.getIntExtra(constant.EXTRA_SURA_POSITION, 0)

        readFileText()
        tv_sura_name.text = SuraName

// TODO: click back 
        imgback.setOnClickListener(View.OnClickListener {
            finish()
        })

    }

    fun readFileText() {
        val fileName = "${SuraPosition?.plus(1)}.txt"
        var FileContent = assets.open(fileName).bufferedReader().use { it.readText() }

        // var SuraContent= FileContent.split('\n')
        tv_sura_content.text = FileContent
        //.toString()

    }
}