package com.sumansoul.module_news.viewModel

import com.sumansoul.base.viewmodel.MvvmBaseViewModel
import com.sumansoul.module_news.httpBean.HomePagerBean
import com.sumansoul.module_news.httpBean.MePagerBean
import com.sumansoul.module_news.model.HomeModel
import com.sumansoul.module_news.model.MePagerModel
import com.sumansoul.module_news.view.TitleViewSModel

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/9
 * Time: 9:58
 */
class MeHomePagerViewModel : MvvmBaseViewModel<MePagerModel, MePagerBean, TitleViewSModel>(){
    init {
        model= MePagerModel()
        model.register(this)
    }
}