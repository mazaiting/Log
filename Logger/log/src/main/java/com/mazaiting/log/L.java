package com.mazaiting.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * 日志工具类
 * Created by mazaiting on 2018/7/19.
 */

public class L {
  /***
   * 日志打印策略
   */
  private static LogStrategy mStrategy = new AndroidStrategy();
  
  /**
   * 设置TAG
   *
   * @param isDebug 是否为Debug模式
   */
  public static void setDebug(boolean isDebug) {
    setProp(isDebug, null);
  }
  
  /**
   * 设置TAG
   *
   * @param isDebug 是否为Debug模式
   * @param tag     标识
   */
  public static void setProp(boolean isDebug, String tag) {
    if (mStrategy instanceof AndroidStrategy) {
      ((AndroidStrategy) mStrategy).setDebug(isDebug);
      if (!TextUtils.isEmpty(tag)) {
        ((AndroidStrategy) mStrategy).setTag(tag);
      }
    }
  }
  
  /**
   * 打印日志级别为VERBOSE
   *
   * @param msg 消息
   */
  public static void v(String msg) {
    mStrategy.log(Log.VERBOSE, msg);
  }
  
  /**
   * 打印日志级别为DEBUG
   *
   * @param msg 消息
   */
  public static void d(String msg) {
    mStrategy.log(Log.DEBUG, msg);
  }
  
  /**
   * 打印日志级别为INFO
   *
   * @param msg 消息
   */
  public static void i(String msg) {
    mStrategy.log(Log.INFO, msg);
  }
  
  /**
   * 打印日志级别为WARN
   *
   * @param msg 消息
   */
  public static void w(String msg) {
    mStrategy.log(Log.WARN, msg);
  }
  
  /**
   * 打印日志级别为ERROR
   *
   * @param msg 消息
   */
  public static void e(String msg) {
    mStrategy.log(Log.ERROR, msg);
  }
  
  /**
   * 打印日志级别为ASSERT
   *
   * @param msg 消息
   */
  public static void a(String msg) {
    mStrategy.log(Log.ASSERT, msg);
  }
}
