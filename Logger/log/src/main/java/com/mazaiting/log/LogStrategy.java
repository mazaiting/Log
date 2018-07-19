package com.mazaiting.log;


import android.support.annotation.NonNull;

/**
 * 日志策略接口
 * Created by mazaiting on 2018/7/19.
 */
interface LogStrategy {
  /**
   * 打印日志
   * @param priority 日志显示级别
   * @param msg 待打印的信息
   */
  void log(int priority, @NonNull String msg);
}
