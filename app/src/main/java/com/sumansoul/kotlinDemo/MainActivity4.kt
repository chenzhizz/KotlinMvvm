package com.sumansoul.kotlinDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView: Any = DataBindingUtil.setContentView(this, R.layout.activity_main4)
    }

    fun reverseKGroup(head: List<Int>, k: Int):  List<Int>? {

        if(k>head.size){
            return null
        }
        for (i in 0..head.size step k ) {
        
        }

        return  null
    }
}