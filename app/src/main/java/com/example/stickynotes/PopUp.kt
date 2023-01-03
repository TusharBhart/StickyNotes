package com.example.stickynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.stickynotes.MainActivity
import com.example.stickynotes.R
import com.google.android.material.tabs.TabLayout

class PopUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup)

        val screen = object : Thread() {
            override fun run() {
                try {

                    Thread.sleep(2100)
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()

                }
            }
        }

        screen.start()



    }
}