package com.sumansoul.module_news.httpBean

import com.sumansoul.network.BaseRepose

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/9/24
 * Time: 13:05
 */
data class PersionData(var data: Data) : BaseRepose() {
    data class Data(
        val attentNum: Int,
        val buysum: Int,
        val fansNum: Int,
        val head: String,
        val height: Double,
        val isAudit: String,
        val limit: Int,
        val nickname: String,
        val point: Int,
        val status: Int,
        val switchStatus: Int,
        val weight: Int
    )
}