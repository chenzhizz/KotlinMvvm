package com.sumansoul.module_news.httpBean

import com.sumansoul.network.BaseRepose

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/9
 * Time: 10:00
 */
data class MePagerBean(var data: Data) : BaseRepose() {
    data class Data(
        val attentNum: Double,
        val buysum: Double,
        val fansNum: Double,
        val head: String,
        val height: Double,
        val isAudit: String,
        val limit: Double,
        val nickname: String,
        val point: Double,
        val status: Double,
        val switchStatus: Double,
        val weight: Double
    )
}