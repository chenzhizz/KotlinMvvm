package com.sumansoul.kotlinDemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import com.sumansoul.kotlinDemo.databinding.ActivityCoroutineBinding
import com.sumansoul.kotlinDemo.viewmodel.CoroutineViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding = DataBindingUtil.setContentView<ActivityCoroutineBinding>(
            this,
            R.layout.activity_coroutine
        )
        var viewmodel = ViewModelProvider(this)[CoroutineViewModel::class.java]
        viewmodel.netData.observe(this, Observer {
            dataBinding.tvText.text = "$it"
        })
        lifecycleScope.launchWhenResumed {
            repeat(100) {
                delay(1000)
                //dataBinding.tvText.text = "$it"
                Log.e("Tag", "$it")
            }
        }
    }

    fun onClickButton(view: View) {
        startActivity(Intent(this, MainActivity5::class.java))
    }
}