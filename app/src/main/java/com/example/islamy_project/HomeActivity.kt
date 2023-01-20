package com.example.islamy_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.islamy_project.databinding.ActivityHomeBinding
import com.example.islamy_project.hadeth.HadethFragment
import com.example.islamy_project.quran.QuranFragment
import com.example.islamy_project.radio.RadioFragment
import com.example.islamy_project.sebha.SebhaFragment

class HomeActivity : AppCompatActivity() {
    lateinit var  viewBinding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        selectedItem()
        viewBinding.contantView.bottomNavigation.selectedItemId=R.id.nav_quraan



    }

    //func used at selected item in bottomnav
    private  fun selectedItem()
    {
        viewBinding.contantView.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_quraan -> {
                    showfragment(QuranFragment())


                }
                R.id.nav_hadeth -> {
                    showfragment(HadethFragment())
                }

                R.id.nav_sebha -> {
                        showfragment(SebhaFragment())
                }
                R.id.nav_radio -> {
                    showfragment(RadioFragment())
                }

            }
            return@setOnItemSelectedListener true

        }

    }
    //run the fragment
    private  fun showfragment(Fragment:Fragment){
        supportFragmentManager
            .beginTransaction()
                //framelayou > the place show on the fragment
            .replace(R.id.FrameLayout_fragment_container,Fragment)
            .commit()


    }
}