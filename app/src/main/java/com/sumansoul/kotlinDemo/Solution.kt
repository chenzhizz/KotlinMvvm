package com.sumansoul.kotlinDemo

import android.util.Log

/**
 *
 * @ProjectName:    KotlinApplication
 * @Package:        com.sumansoul.kotlinDemo
 * @ClassName:      Solution
 * @Description:    描述
 * @Author:         chenzhi
 * @CreateDate:     2021/7/19 15:55
 * @UpdateUser:     chenzhi
 * @UpdateDate:     2021/7/19 15:55
 * @UpdateRemark:   chenzhi
 * @Version:         2.4
 */
public class Solution {

    fun change(amount: Int, coins: IntArray): Int {
//        amount = 5, coins = [1, 2, 5]
        var domin= IntArray(amount+1)
        coins.forEach {
            for (j in 1..amount){

                if(j>=it){
                    domin[j]=domin[j]+domin[j-it]
                }
            }
        }
        return domin[amount]
    }


}

fun main() {
    var mSolution=Solution()

    Log.e("mSolution",   "" +mSolution.change(5,intArrayOf(1,2,5)))
}