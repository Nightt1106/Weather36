package com.night.weather36

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        textView_start_time.text = intent.getStringExtra("startTime")
        textView_end_time.text = intent.getStringExtra("endTime")
        textView_temp.text = intent.getStringExtra("parameterName")
    }
}
