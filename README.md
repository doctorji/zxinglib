# zxinglib
封装的二维码条形码扫描,二维码生成，识别图片中二维码的类库


二维码在生活中非常的普遍，这个库基于最新的Google ZXing 3.3.2jar包来实现


使用方法:



1.在项目的project 目录下的build.gradle 中的allprojects加上 maven { url 'https://jitpack.io' }
allprojects {
    repositories {
        jcenter()
        maven { url 'https://maven.google.com' }
        maven { url 'https://jitpack.io' }
    }
}



2.在自己项目的module中的build.gradle目录下，加上 compile 'com.github.doctorji:zxinglib:V1.0'
