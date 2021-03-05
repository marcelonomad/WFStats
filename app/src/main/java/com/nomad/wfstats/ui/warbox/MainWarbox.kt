package com.nomad.wfstats.ui.warbox

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nomad.wfstats.R
import kotlinx.android.synthetic.main.activity_main_warbox.*

class MainWarbox : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_warbox)

        /*imgTest.setBackgroundResource(R.drawable.warbox_anim);
        val anim: AnimationDrawable = imgTest.background as AnimationDrawable
        anim.start()*/

        btnMyWarbox.setOnClickListener {
        }
        btnOpenWarbox.setOnClickListener {
            val i = Intent(this, OpenWarbox::class.java)
            startActivity(i)
        }
    }
}