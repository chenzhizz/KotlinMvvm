package com.sumansoul.module_news.api

import com.sumansoul.network.BaseRepose


data class NeawsChannesBean(
    var base:BaseRepose)
{
    data class Data(
        val Activities: List<Activity>,
        val Advertising: List<Advertisin>,
        val `class`: List<Clas>,
        val good_name: List<GoodName>,
        val is_audit: Int,
        val is_class: Int,
        val switchStatus: Int,
        val teacher: List<Teacher>
    ) {
        data class Activity(
            val _id: String,
            val applet_appId: String,
            val create_time: String,
            val description: String,
            val end_time: String,
            val img_path: String,
            val jump_type: Int,
            val jump_url: String,
            val local: Int,
            val name: String,
            val show_type: Int,
            val sort: Int,
            val start_time: String,
            val status: Int,
            val update_time: String
        )

        data class Advertisin(
            val _id: String,
            val applet_appId: String,
            val create_time: String,
            val description: String,
            val end_time: String,
            val img_path: String,
            val jump_type: Int,
            val jump_url: String,
            val local: Int,
            val name: String,
            val show_type: Int,
            val sort: Int,
            val start_time: String,
            val status: Int,
            val update_time: String
        )

        data class Clas(
            val chat_room_code: String,
            val class_begin_time: String,
            val class_end_time: String,
            val class_level: String,
            val class_name: String,
            val class_total: Int,
            val count: Int,
            val end_date: String,
            val goods_name: String,
            val head_img: String,
            val id: String,
            val standard: Int,
            val start_date: String,
            val status: Int,
            val teacher_nickname: String
        )

        data class GoodName(
            val classified: Int,
            val goods_name: String,
            val status: Int
        )

        data class Teacher(
            val _id: String,
            val avg_score: Double,
            val back_img: String,
            val banner: Any,
            val card_back_img: String,
            val card_front_img: String,
            val describe: String,
            val duration_time: Int,
            val front_img: String,
            val half_img: String,
            val head_img: String,
            val index_background: String,
            val is_home: Int,
            val name: String,
            val nickname: String,
            val side_img: String,
            val specialty: String,
            val standard: Int,
            val status: String,
            val sum_score: Double,
            val sum_student: Int
        )
    }
}