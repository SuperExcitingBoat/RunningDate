#---------------------------------1.实体类---------------------------------

-keep public class * extends android.support.v4.app.Fragment


-keep public class * extends android.app.Fragment
#-keepclasseswithmembernames class * { # 保持native方法不被混淆
 #   native <methods>;
#}
#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------
#Map
-dontwarn AMap2DMap_2.9.2_AMapLocation_3.1.0_20161027.*
-dontwarn AMap_Location_V2.4.1_20160414.*
-keep class AMap2DMap_2.9.2_AMapLocation_3.1.0_20161027.** { *;}
-keep class AMap_Location_V2.4.1_20160414.** { *;}

-dontwarn com.amap.api.**
-dontwarn com.a.a.**
-dontwarn com.autonavi.**

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
#okhttp with retrofit

-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.** { *; }
-dontwarn com.squareup.okhttp3.**


-keep class okhttp3.** { *; }

-keep interface okhttp3.** { *; }

-dontwarn okhttp3.**

-dontwarn rx.**
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keep class sun.misc.Unsafe { *; }

-dontwarn okio.**
-dontwarn com.squareup.retrofit2.**
-dontwarn retrofit.appengine.UrlFetchClient

-keep class com.tbruyelle.rxpermissions.**{*;}
-keep class rxpermissions.**{*;}
-keep interface rxpermissions.**{*;}

#rxjava
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#rxandroid
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-keepclassmembers class rx.internal.util.unsafe.** {
    long producerIndex;
    long consumerIndex;
}
-dontwarn rx.internal.util.unsafe.**

#RxCache
-dontwarn io.rx_cache.internal.**
-keepclassmembers enum io.rx_cache.Source { *; }

#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
#-keep public class * extends android.app.Activity
#-keep public class * extends android.app.AppCompatActivity
#-keep public class * extends android.app.Application
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
#-keep public class * extends android.content.ContentProvider
#-keep public class * extends android.app.backup.BackupAgentHelper
#-keep public class * extends android.preference.Preference
#-keep public class * extends android.view.View
#-keep public class com.android.vending.licensing.ILicensingService
#-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    }
    -keep class * implements android.os.Parcelable {
      public static final android.os.Parcelable$Creator *;
    }
    -keepclassmembers class * implements java.io.Serializable {
        static final long serialVersionUID;
        private static final java.io.ObjectStreamField[] serialPersistentFields;
        private void writeObject(java.io.ObjectOutputStream);
        private void readObject(java.io.ObjectInputStream);
        java.lang.Object writeReplace();
        java.lang.Object readResolve();
    }
    -keep class **.R$* {
     *;
    }
    -keepclassmembers class * {
        void *(**On*Event);
    }

-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.** { *; }

-dontwarn com.squareup.okhttp3.**
-keep class okhttp3.** { *; }

-keep interface okhttp3.** { *; }

-dontwarn okhttp3.**

-dontwarn rx.**
-dontwarn retrofit2.**
-keep class rx.** { *; }
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keep class com.superexcitingboat.runningdate.network.service.** { *; }

-keep class sun.misc.Unsafe { *; }

-dontwarn okio.**
-dontwarn com.squareup.retrofit2.**
-dontwarn retrofit.appengine.UrlFetchClient

-keep class com.tbruyelle.rxpermissions.**{*;}
-keep class rxpermissions.**{*;}
-keep interface rxpermissions.**{*;}

#gayson
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# ---------------------------------------------------------------------------







#-libraryjars libs/AMap2DMap_2.9.2_AMapLocation_3.1.0_20161027.jar
#-libraryjars libs/AMap_Location_V2.4.1_20160414.jar

#定位
#-keep class com.amap.api.location.**{*;}
#-keep class com.loc.**{*;}
#-keep class com.amap.api.fence.**{*;}
#-keep class com.autonavi.aps.amapapi.model.**{*;}

# 2D地图
#-keep class com.amap.api.maps2d.**{*;}
#-keep class com.amap.api.mapcore2d.**{*;}
#-keep class com.amap.api.**{*;}

 # 3D 地图
#-keep class com.amap.api.maps.**{*;}
#-keep class com.autonavi.amap.mapcore.*{*;}
#-keep class com.amap.api.trace.**{*;}

#定位
#-keep class com.amap.api.location.**{*;}
#-keep class com.amap.api.fence.**{*;}
#-keep class com.autonavi.aps.amapapi.model.**{*;}

   # 搜索
#-keep class com.amap.api.services.**{*;}

  #  2D地图
#-keep class com.amap.api.maps2d.**{*;}
#-keep class com.amap.api.mapcore2d.**{*;}

   # 导航
#-keep class com.amap.api.navi.**{*;}
#-keep class com.autonavi.**{*;}

#-keep class com.amap.**{*;}
