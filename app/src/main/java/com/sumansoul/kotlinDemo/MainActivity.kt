package com.sumansoul.kotlinDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.sumansoul.network.NetWorkApi
import com.sumansoul.network.observer.BaseObserver
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var str:String="This is KT file thanks JB ok?"
    var br :Boolean=true
    var a:Int=2222
    val b:Int=5555
    var strL :String? ="abc"
    var gson= Gson()
    val TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_text.visibility= View.VISIBLE
        tv_text.text=str
        iv_image.setImageResource(R.drawable.ic_launcher_background)
        iv_image.visibility=View.VISIBLE
        a=3
        NetWorkApi.init(AppNetWork())
//        SSNetWorkApi.getService(NewsApiInterface::class.java)
//            .getNewsChagnes()
//            .compose(SSNetWorkApi.getInstance().applySchedluers(object :BaseObserver<NeawsChannesBean>(){
//                override fun onSuccess(t: NeawsChannesBean) {
//                    Log.e(TAG,gson.toJson(t))
//                }
//
//                override fun onFailure(e: Throwable) {
//                    Log.e(TAG,gson.toJson(e))
//                }
//
//            }))
//        NetWorkApi.getService(NewsApiInterface::class.java)
//            .getNewsChagnes()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object :BaseObserver<NeawsChannesBean>(){
//                override fun onSuccess(t: NeawsChannesBean) {
//                   Log.e(TAG,gson.toJson(t))
//                }
//
//                override fun onFailure(e: Throwable) {
//                    Log.e(TAG,gson.toJson(e))
//                }
//
//            })
//        NetWorkApi.getTencentNewChannes(object :BaseObserver<NeawsChannesBean>(){
//            override fun onSuccess(t: NeawsChannesBean) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(e: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })

        when (a) {
            1 -> {
                println("1----------------"+a)
            }
            2 -> { println("2---------------"+a)
            }
            3 -> { println("3------------"+a)
            }
            4 -> { println("4--------------"+a)
            }
            else -> { println("else------------"+a)
            }
        }

        strL=null
        strL?.length?: println()
        for(i in 100 downTo 90 step 1){
            println("------------------->  "+i+ "             "+sayHello(strL?.toString()?:"ceshi"))
        }
    }
      fun  sayHello(name:String?): String {
        return "Hello, $name"
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }
}