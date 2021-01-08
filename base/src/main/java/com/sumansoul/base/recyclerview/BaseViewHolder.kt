package com.sumansoul.base.recyclerview



import android.view.View
import androidx.recyclerview.widget.RecyclerView.*
import com.sumansoul.base.customview.ICustomView
import com.sumansoul.base.customview.BaseCustomModel
import com.sumansoul.base.customview.ICustViewActionListener

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/8/5
 * Time: 17:39
 */
class BaseViewHolder< T: BaseCustomModel>: ViewHolder {

    lateinit var view: ICustomView<T>
    constructor(view: ICustomView<T>) : super(view as View){
        this.view=view
    }
    fun  bind(item:T){
       view.setData(item)
    }
}