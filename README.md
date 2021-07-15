![img](https://github.com/WWGsky/OneByOneLayout/blob/master/img/3a63aca86cb1eeba8c43129787f9034.jpg)

## 多个输入框的组合
> 一个又一个的输入框组合,适用于密码输入
> 小米手机输入密码的时候录屏是黑的,就先截图了

特点:
1. 可随意指定输入框个数
2. 可自定义输入框背景
3. 自动排版

## Attributes属性
>在xml文件中使用

|Attributes|format|describe|defaultValue
|---|---|---|---|
|inputNum|integer|输入框个数|6
|inputWidth|dimension|输入框宽度|wrap_content
|inputHeight|dimension|输入框高度|wrap_content
|inputMaxLength|integer|输入框最大输入位数|1
|inputBackGround|reference&&color|输入框背景|null(0)
|inputCursorDrawable|reference|输入框光标(API29以上生效)|null(0)

## Config设置
>样式初始化
>config.setXXX()
>例如: config.setInputNum(6);

|属性|作用|默认值|
|---|---|---|
|inputNum|输入框个数|6|
|inputWidth|输入框宽度|WRAP_CONTENT|
|inputHeight|输入框高度|WRAP_CONTENT|
|inputMaxLength|输入框最大输入位数|1|
|inputBackGround|输入框背景|null(0)|
|inputCursorDrawable|输入框光标资源(Api等级大于或等于29时,才会生效)|null(0)|
|inputType|输入框输入模式||
|inputGravity|输入框对其方式|Gravity.CENTER|
|inputListener|输入监听||

## 方法
>代码中调用

|属性|作用|
|---|---|
|getConfig()|获取设置类|
|setInputListener()|设置输入回调监听|
|getInputInfo()|获取全部输入的内容|
|clearInputInfo()|清除全部输入内容|



# 使用`JitPack`的方式, 引入库.

## 根目录中的 `build.gradle`

```java
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

## APP目录中的 `build.gradle`

```java
dependencies {
    implementation 'com.github.WWGsky:OneByOneLayout:1.0.0'
}
```

# 使用
## xml设置属性
>这里用到的一些资源文件在代码中都能找到,开发者可根据需求自定义
```html
<com.wwg.onebyoneinputlayout.OneByOneInputLayout
        android:id="@+id/inputLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="10dp"
        android:layout_centerInParent="true"
        android:background="#ff8080"
        android:padding="10dp"
        app:inputNum="6"
        app:inputWidth="36dp"
        app:inputHeight="36dp"
        app:inputMaxLength="1"
        app:inputBackGround="@drawable/selector_app_theme_ed_focus_change"
        app:inputCursorDrawable="@drawable/app_theme_ed_cursor_round"/>
```
## 代码中设置
>在此仅为使用示例,具体以需求为准
```java
OneByOneInputLayout inputLayout = findViewById(R.id.inputLayout);
inputLayout.getConfig().setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
```
## 设置输入监听
>只有设置了输入监听后,才会绘制哦!!!!
```java
OneByOneInputLayout inputLayout = findViewById(R.id.inputLayout);

//设置输入监听
inputLayout.setInputListener(new OneByOneInputListener() {
    @Override
    public void getInputInfo(String info) {

        Toast.makeText(MainActivity.this, "输入信息 --> " + info, Toast.LENGTH_SHORT).show();
    }
});
```
