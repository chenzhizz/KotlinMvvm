package com.sumansoul.kotlinDemo;

import com.sumansoul.network.INetWorkRequiredInfo;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/24
 * Time: 12:03
 */
class AppNetWork implements INetWorkRequiredInfo {
    @NotNull
    @Override
    public String getGetAppVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    @Override
    public void setGetAppVersionName(@NotNull String getAppVersionName) {

    }

    @NotNull
    @Override
    public String getGetAppVersionCode() {
        return String.valueOf( BuildConfig.VERSION_CODE);
    }

    @Override
    public void setGetAppVersionCode(@NotNull String getAppVersionCode) {

    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void setDebug(boolean isDebug) {

    }
}
