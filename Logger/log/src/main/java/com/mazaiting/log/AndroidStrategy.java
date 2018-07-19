package com.mazaiting.log;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Android 日志策略
 * Created by mazaiting on 2018/7/19.
 */
public class AndroidStrategy implements LogStrategy {
  /*******************************************************************************
   ********************************* 图形 ****************************************
   *******************************************************************************/
  private static final char TOP_LEFT_CORNER = '┏';
  private static final char TOP_RIGHT_CORNER = '┓';
  private static final char BOTTOM_LEFT_CORNER = '┗';
  private static final char BOTTOM_RIGHT_CORNER = '┛';
  private static final char MIDDLE_LEFT_CORNER = '┣';
  private static final char MIDDLE_RIGHTCORNER = '┫';
  private static final char HORIZONTAL_LINE = '┃';
  private static final String DOUBLE_DIVIDER = "━━━━━━━━━━━━━━━━━━━━━━━━━━━";
  private static final String SINGLE_DIVIDER = "┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅";
  
  /**
   * 方法偏移量
   */
  private static final int METHOD_OFFSET = 0;
  /**
   * 打印每一行最多字符个数
   */
  private static final int LENGTH = 88 - 1;
  /**
   * 最小堆栈偏移
   */
  private static final int MIN_STACK_OFFSET = 5;
  /**
   * 中部横线
   */
  private String mMiddleBorder = MIDDLE_LEFT_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;
  /**
   * TAG 标识
   */
  private String mTag = "LOGGER";
  /**
   * 方法个数
   */
  private int methodCount = 2;
  /**
   * 是否为调试模式。true,调试；false,非调试
   */
  private boolean isDebug = true;
  
  /**
   * 设置TAG
   *
   * @return 当前类对象
   */
  AndroidStrategy setTag(String tag) {
    this.mTag = tag;
    return this;
  }
  
  /**
   * 是否是否为调试模式
   *
   * @param isDebug true，调试; false, 非调试
   */
  AndroidStrategy setDebug(boolean isDebug) {
    this.isDebug = isDebug;
    return this;
  }
  
  /**
   * 日志打印
   */
  @Override
  public void log(int priority, @NonNull String msg) {
    // 是否为调试模式
    if (isDebug) {
      // 打印线程信息
      logThreadInfo(priority);
      // 打印类信息
      logClassInfo(priority);
      // 打印消息
      logMessageInfo(priority, msg);
    }
  }
  
  /**
   * 打印消息信息
   *
   * @param priority 日志级别
   * @param msg      信息
   */
  private void logMessageInfo(int priority, String msg) {
    // 获取消息总长度
    int tLen = msg.length();
    // 获取消息长度打印长度的倍数
    int multiple = tLen / LENGTH + 1;
    // 创建临时变量
    String tmp;
    // 遍历倍数
    for (int i = 0; i < multiple; i++) {
      // 获取字符内容
      if (i != multiple - 1) {
        // 获取指定范围的数据
        tmp = msg.substring(i * LENGTH, (i + 1) * LENGTH);
      } else {
        // 最后一段数据
        tmp = msg.substring(i * LENGTH);
      }
      // 打印消息
      Log.println(priority, mTag, HORIZONTAL_LINE + tmp);
    }
    // 打印底部横线
    String bottomBorder = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    Log.println(priority, mTag, bottomBorder);
  }
  
  /**
   * 打印类信息
   *
   * @param priority 日志级别
   */
  private void logClassInfo(int priority) {
    // 获取线程的堆栈
    StackTraceElement[] trace = Thread.currentThread().getStackTrace();
    // 获取堆栈偏移量
    int stackOffset = getStackOffset(trace) + METHOD_OFFSET;
    // 检验方法数是否合法
    if (methodCount + stackOffset > trace.length) {
      methodCount = trace.length - stackOffset - 1;
    }
    // 等级偏移
    String level = "";
    // 方法数遍历
    int i = methodCount;
    // 拼接字符串
    StringBuilder builder;
    // 循环
    while (i > 0) {
      // 获取堆栈标识
      int stackIndex = (i) + stackOffset;
      // 判断堆栈标识是否合法
      if (stackIndex >= trace.length) continue;
      // 获取堆栈标识对应的数据
      StackTraceElement element = trace[stackIndex];
      // 创建StringBuilder
      builder = new StringBuilder();
      // 拼接方法所在的类及方法名，行数
      builder.append(HORIZONTAL_LINE).append("").append(level).append(element.getClassName().substring(element.getClassName().lastIndexOf(".") + 1))
              .append(".").append(element.getMethodName()).append(" ").append(" (").append(element.getFileName())
              .append(":").append(element.getLineNumber()).append(")");
      // 添加每行的换行
      level += "    ";
      // 打印当前行数据
      Log.println(priority, mTag, builder.toString());
      // 进行自减
      i--;
    }
    // 打印中间行
    Log.println(priority, mTag, mMiddleBorder);
  }
  
  /**
   * 打印线程信息
   *
   * @param priority 日志级别
   */
  private void logThreadInfo(int priority) {
    // 打印顶部横线
    String topBorder = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    Log.println(priority, mTag, topBorder);
    // 是否显示线程信息
    Log.println(priority, mTag, HORIZONTAL_LINE + "Thread: " + Thread.currentThread().getName());
    // 打印中间分割线
    Log.println(priority, mTag, mMiddleBorder);
  }
  
  /**
   * 获取堆栈偏移量
   *
   * @param trace 堆栈数组
   * @return 返回堆栈偏移量
   */
  private int getStackOffset(StackTraceElement[] trace) {
    // 判断堆栈信息是否为空
    if (trace != null) {
      // 设置最小堆栈偏移
      int index = MIN_STACK_OFFSET;
      // 遍历
      for (StackTraceElement element : trace) {
        // 判断是否为本类
        if (!L.class.getName().equals(element.getClassName())) {
          return --index;
        }
        // 变量自增
        index++;
      }
    }
    return -1;
  }
}
