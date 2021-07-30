# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 假如项目中有用到注解，保留注解，不混淆
-keepattributes *Annotation*

# 混淆前认证，去掉可加快混淆速度
# -dontpreverify

#Proguard对你的代码进行迭代优化的次数 0~7，一直优化到代码不能被优化为止
#-optimizationpasses 5

# release版不打印log
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
    public static *** w(...);
}

# 确保JavaBean不被混淆-否则Gson将无法将数据解析成具体对象
-keep class y.yj.you.bean** { *; }

# ARouter路由框架
-keep public class com.alibaba.android.arouter.routes** { *; }
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 忽略警告