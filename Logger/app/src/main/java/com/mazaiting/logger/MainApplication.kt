package com.mazaiting.logger

import android.app.Application
import com.mazaiting.log.L

/**
 * 应用入口
 * Created by mazaiting on 2018/7/19.
 */
class MainApplication : Application() {
  override fun onCreate() {
    super.onCreate()
//    L.setProp(BuildConfig.DEBUG, "MainActivity")
    L.setDebug(BuildConfig.DEBUG)
  }
}