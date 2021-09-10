package com.sumansoul.common.arounter

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/19
 * Time: 11:32
 */
interface RouterConfig {

    /*群组---分类*/
    interface group {
        companion object {
            const val NEED_LOGIN = "needLogin"
        }
    }
    interface App {
        companion object {
           /*主页*/
            const val ACTIVITY_MAIN = "/app/MainActivity"
            const val ACTIVITY_LOGIN = "/app/MainActivity"
        }
    }
}