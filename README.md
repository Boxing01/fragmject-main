*Read this in [English](README.EN.md)*

## 前言
刚开始学习 `Kotlin` 其实挺痛苦的，相关的书籍或视频偏向于知识点的讲解，没有完整的项目实操。   
开源项目业务复杂，代码层层封装，用来上手实在不合适，于是便有了 `fragmject` 项目。   
在此感谢 [玩Android](https://www.wanandroid.com/) 及其提供 [开放API](https://wanandroid.com/blog/show/2) 。

## 简介
`fragmject` 是一个为初学者准备的上手项目。   
通过对 `Kotlin` 和 `Compose` 的系统运用，实现的一个功能完备符合主流市场标准应用。   
`fragmject` 没有复杂的业务和多余的封装， 完全依照 [Android Developer](https://developer.android.google.cn/) 官方的写法。   
代码简单，内容全面，快速上手，对理解其他项目设计思想和封装技巧也很有帮助。

学习本项目你将有如下收获：
- Kotlin
- Compose
- MVVM、MVI
- 常用控件封装(图片选择器、图片编辑器、日期控件、全面屏沉浸、屏幕录制...)
- 字节码插桩(ASM...)

## 开发环境
为了您能正常运行本项目，请先更新你的 `Android Studio` 。
- [master](https://github.com/miaowmiaow/fragmject/tree/master) 分支需 `Android Studio Giraffe | 2022.3.1 Patch 3` 及以上版本运行(可能需要梯子)。 [Download Android Studio | Android Developer](https://developer.android.google.cn/studio?hl=en)
- [dev](https://github.com/miaowmiaow/fragmject/tree/dev) 分支需最新预览版本 `Android Studio Iguana | 2023.2.1.13` 及以上版本运行(可能需要梯子)。 [Download Android Studio | Android Developer](https://developer.android.google.cn/studio/preview?hl=en)
- 您也可以自行配置 `AGP` 和 `compose`。 [libs.versions.toml](https://github.com/miaowmiaow/fragmject/blob/master/gradle/libs.versions.toml)

## 将 Android 应用迁移到版本目录
[将 build 迁移到版本目录 | Android Developer](https://developer.android.google.cn/studio/build/migrate-to-catalogs?hl=zh-cn)

## 前置知识
在学习前希望您能了解以下知识，这将帮助您更快的上手本项目。
- [Kotlin 语言学习 | Android Developer](https://developer.android.google.cn/kotlin/learn?hl=zh_cn)
- [Kotlin 代码示例 | Android Developer](https://play.kotlinlang.org/byExample/overview)
- [ViewBinding 使用入门 | Android Developer](https://developer.android.google.cn/topic/libraries/view-binding?hl=zh-cn)
- [LiveData 使用入门 | Android Developer](https://developer.android.google.cn/topic/libraries/architecture/livedata?hl=zh_cn)
- [ViewModel 使用入门 | Android Developer](https://developer.android.google.cn/topic/libraries/architecture/viewmodel?hl=zh_cn)
- [Coroutines 使用入门 | Android Developer](https://developer.android.google.cn/kotlin/coroutines?hl=zh_cn)
- [Navigation 使用入门 | Android Developer](https://developer.android.google.cn/guide/navigation/navigation-getting-started?hl=zh_cn)
- [Room 使用入门 | Android Developer](https://developer.android.google.cn/training/data-storage/room?hl=zh_cn)
- [Compose 使用入门 | Android Developer](https://developer.android.google.cn/jetpack/compose)

## 为什么很少依赖其他库
在日常开发中我推荐使用 `Hilt` 、 `Paging` 等库，不仅提高效率也能减少bug。   
但是初学者过早依赖其他库，可能会有以下危害：
- 增加学习负担，其他库用起来简单但是底层实现往往复杂，阅读源码容易打击学习积极性。
- 造成基础薄弱，初学者容易把其他库能力当成自己的能力，脱离其他库开发能力大大下降。

因此，本项目尽量多去自己实现，可能不是很优雅但一定能让你学习到更多。

## 截图展示
| ![1.jpg](https://raw.githubusercontent.com/miaowmiaow/fragmject/master/screenshot/1.png) | ![2.jpg](https://raw.githubusercontent.com/miaowmiaow/fragmject/master/screenshot/2.png) | ![3.jpg](https://raw.githubusercontent.com/miaowmiaow/fragmject/master/screenshot/3.png) |
| ------------------------------------------------------------ |------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------|

## 项目目录结构
```
├── app                                         app
|  └── src 
|     └── main 
|     |   └── java                              源码目录
|     |   |  ├── bean                           bean目录
|     |   |  ├── components                     自定义组件目录
|     |   |  ├── ui                             ui目录
|     |   |  |  └── main                        mian目录
|     |   |  |     └── home                     home目录
|     |   |  |     |  ├── HomeScreen      
|     |   |  |     |  └── HomeViewModel   
|     |   |  |     └── MainScreen          
|     |   |  ├── utils                          工具类目录
|     |   |  ├── WanActivity                    唯一Activity
|     |   |  ├── WanApplication                 Application
|     |   |  ├── WanTheme                       Theme
|     |   |  ├── WanNavGraph                    导航图
|     |   |  └── WanViewModel                   ViewModel
|     |   |
|     |   └── res                               资源目录
|     |   └── AndroidManifest.xml               配置文件
|     |
|     ├── build.gradle                          模块构建配置
|     ├── dictionary                            自定义混淆字典
|     └── proguard-rules.pro                    代码混淆配置文件
| 
├── library-base                                基础library（library开头为公共库，任何项目都可使用）
|  └── src 
|     └── main 
|     |   ├── assets                            assets目录
|     |   └── java                              源码目录
|     |      ├── activity                       Activity目录
|     |      ├── adapter                        Adapter目录
|     |      ├── bus                            消息总线目录
|     |      ├── db                             Database目录
|     |      ├── dialog                         Dialog目录
|     |      ├── http                           网络请求目录
|     |      ├── provider                       ContentProvider目录
|     |      ├── service                        Service目录
|     |      ├── utils                          工具类目录
|     |      └── view                           自定义view目录
|     | 
|     └── build.gradle                          模块构建配置
| 
├── library-picture                             图片模块（目录同app，不再展开）
| 
├── library-plugin                              插件模块
|  └── src 
|     └── main 
|        ├── kotlin                             源码目录
|        └── resources                          配置目录
|           └── statistic.properties            插件配置
| 
├── repos                                       插件生成目录
|
├── build.gradle                                项目构建配置
├── config.properties                           项目配置
├── gradle.properties                           gradle配置
└── settings.gradle                             项目依赖配置
```
## 主要开源库
- [coil-kt/coil](https://github.com/coil-kt/coil)
- [google/gson](https://github.com/google/gson)
- [square/okhttp](https://github.com/square/okhttp)
- [square/retrofit](https://github.com/square/retrofit)

