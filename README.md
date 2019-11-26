# Log
The simplest log print in history, 已弃用. 改用[EasyAndroid](https://github.com/mazaiting/EasyAndroid)

# 使用
- 简单使用
在需要使用的地方直接调用
```
L.e("mazaiting");
```
- 设置调试
建议在Application的实现类中调用
```
// 设置为调试，release版本将不会有日志打印
L.setDebug(BuildConfig.DEBUG)
```
- 设置调试与自定义TAG
```
// 参数一： 设置是否调试
// 参数二： 设置TAG
L.setProp(BuildConfig.DEBUG, "MainActivity")
```
