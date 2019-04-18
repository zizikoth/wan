# wan
🏀玩安卓 kotlin客户端


这个app完全是自己在空闲的时间写的，首先内容很简单，主要就是一个资讯列表，点击进入详情页，这么点内容。自己在开始准备的时候就是将这个项目使用模块化构建，让每一个模块可以单独拿出运行，这个就是这个项目的主要目的了。

## 1.模块化配置

### 1.1 gradel.properties
在创建多个Module之后需要多每一个Module中的是否可以单独运行进行单独配置,如果weitrue说明该Module是可以单独运行的
```
#
# 判断当前模块可以单独运行
#
# 通用模块
moduleCommonRunAlone=false
# Login模块
moduleLoginRunAlone=false
# Article模块
moduleArticleRunAlone=false
# Mine模块
moduleMineRunAlone=false
```
### 1.2 build.gradle
对于每一个Module来说，所使用的SdkVersion也是需要配置，这里使用全局配置，以免造成不同版本可能带来的编译问题，同时也方便以后对版本进行升级。下面是一个Module和app的示例

在Module中  
可运行需要使用application，作为lib导入需要使用library   
可运行需要使用applicationId，这里使用主application拼接单独模块名称作为applicationId，作为lib不需要   
对于是否可单独运行使用的清单文件也是不同的，单独运行时需要配置application信息和启动页面，lib则不需要，所以需要使用两个不同的清单文件  
对于模块来说，也是需要依赖一些数据，比如登陆后的用户信息，所以单独运行的时候还依赖了其他模块，在使用的时候还是使用路由进行界面跳转等操作

```
if (moduleMineRunAlone.toBoolean()) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: 'kotlin-kapt'

android {
    compileSdkVersion rootProject.ext.root.compileSdkVersion
    defaultConfig {
        if (moduleMineRunAlone.toBoolean()) {
            applicationId rootProject.ext.root.applicationId + project.getName()
        }
        minSdkVersion rootProject.ext.root.minSdkVersion
        targetSdkVersion rootProject.ext.root.targetSdkVersion
        versionCode rootProject.ext.root.versionCode
        versionName rootProject.ext.root.versionName
        testInstrumentationRunner rootProject.ext.test.testInstrumentationRunner
        multiDexEnabled true
    }

    sourceSets {
        main {
            if (moduleMineRunAlone.toBoolean()) {
                manifest.srcFile 'src/main/manifest/application/AndroidManifest.xml'
            } else {
                manifest.srcFile 'src/main/manifest/module/AndroidManifest.xml'
            }
        }
    }
}

kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.getName())
    }
}

dependencies {
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    testImplementation rootProject.ext.testLib
    androidTestImplementation rootProject.ext.androidTestLib
    kapt rootProject.ext.routerLib
    if (moduleMineRunAlone.toBoolean()) {
        if (!moduleCommonRunAlone.toBoolean()) implementation project(':moduleCommon')
        if (!moduleLoginRunAlone.toBoolean()) implementation project(':moduleLogin')
        if (!moduleArticleRunAlone.toBoolean()) implementation project(':moduleArticle')
    }
    implementation project(':iFrameBase')
}

```
在App中
主要是一个装载壳，个人喜欢把启动，导航，和主界面放在app中   
判断每一个module是否作为lib导入，进行依赖
```
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: 'kotlin-kapt'

android {
    compileSdkVersion rootProject.ext.root.compileSdkVersion
    defaultConfig {
        applicationId rootProject.ext.root.applicationId
        minSdkVersion rootProject.ext.root.minSdkVersion
        targetSdkVersion rootProject.ext.root.targetSdkVersion
        versionCode rootProject.ext.root.versionCode
        versionName rootProject.ext.root.versionName
        testInstrumentationRunner rootProject.ext.test.testInstrumentationRunner
        multiDexEnabled true
    }

    buildTypes {
        debug {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
        release {
            minifyEnabled true
            shrinkResources true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

}

kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.getName())
    }
}

dependencies {
    testImplementation rootProject.ext.testLib
    androidTestImplementation rootProject.ext.androidTestLib
    kapt rootProject.ext.routerLib
    if (!moduleCommonRunAlone.toBoolean()) implementation project(':moduleCommon')
    if (!moduleArticleRunAlone.toBoolean()) implementation project(':moduleArticle')
    if (!moduleMineRunAlone.toBoolean()) implementation project(':moduleMine')
    if (!moduleLoginRunAlone.toBoolean()) implementation project(':moduleLogin')
    implementation project(':iFrameBase')
}

```

### 1.3 AndroidManifest.xml
在Module中不需要使用Application信息，在单独运行时需要使用Application信息以及启动界面，下面是两个清单文件  

可以单独运行时候的示例
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.memo.article">
    
    <application
        android:name="com.memo.iframe.base.application.BaseApplication"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/IFrameTheme"
        tools:ignore="AllowBackup,GoogleAppIndexingWarning"
        tools:replace="android:label,theme">
        
        <activity
            android:name=".launcher.MainActivity"
            android:screenOrientation="portrait">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity
            android:name=".ui.activity.search.SearchActivity"
            android:screenOrientation="portrait" />
        <activity
            android:name=".ui.activity.result.SearchResultActivity"
            android:screenOrientation="portrait" />
        <activity
            android:name=".ui.activity.favorite.FavoriteActivity"
            android:screenOrientation="portrait" />
    </application>

</manifest>
```

作为Module导入的示例
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.memo.article">
    
    <application tools:ignore="AllowBackup,GoogleAppIndexingWarning,MissingApplicationIcon">
        <activity
            android:name=".ui.activity.tree.TreeArticleActivity"
            android:screenOrientation="portrait" />
        <activity
            android:name=".ui.activity.search.SearchActivity"
            android:screenOrientation="portrait" />
        <activity
            android:name=".ui.activity.result.SearchResultActivity"
            android:screenOrientation="portrait" />
        <activity
            android:name=".ui.activity.favorite.FavoriteActivity"
            android:screenOrientation="portrait" />
    </application>

</manifest>
```

## 2.API接口
[玩Android 开放API  https://www.wanandroid.com/blog/show/2](https://www.wanandroid.com/blog/show/2)

## 3.开发历程
1.开始是把base包导入，然后进行一些列的配置。    
2.对于项目开始大概了解，创建了article，mine，login三个模块，大部分的内容都是文章内容，包括列表，内容，收藏等功能。之后在进行项目的过程中发县，文章的内容是一个网址，于是就单独创建了common，可以作为一个公用的模块，比如WebActivity传入标题和url，就不必单独放在article中，以后如果有可以通用的页面或者是一个公用数据，因为当时想base是所有项目都使用的，所以可能对于某些项目中还需要进行改造，可以不去动base中的数据（想使用仓库导入），直接添加common进行改造，然而事实是我懒了，直接在base中改了，我忏悔    
3.一开始我是把所有的Api都写在common中，因为当前项目的api数量比较少，一般一个项目100+的接口应该是比较常见的，所以把ApiService进行拆分，在不同的模块中都创建一个ApiService，这样可以虽然接口分开了，但是只要把不同的接口对应不同的Api接口，通用接口放在common中，那么还是很好找的，对于不同模块间调用Api接口，可以使用的ARouter的IProvider，这里没有用到    
4.接下来就是正常的写bug和修改bug的过程了，如果Module需要单独使用，在每一个模块中的启动页面中进行配置，跳转到相关页面获取放入相关fragment    
5.使用git，创建了master和dev分支，平时都在dev上开发，在一个阶段完成之后合并到master上
6.对于出现的问题我只能求助google和stackoverflow了,对于所有的图标都是从别的App上copy过来的，界面的话就两种，1阴影，2圆角，够了够了

## 4.项目
有兴趣的朋友可以看看，毕竟还是比较简单的项目，东西不多，如果可以对你有点启发，我也是很开心的能够帮助你  
[项目地址 https://github.com/zmemo/wan](https://github.com/zmemo/wan)
