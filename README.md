# AppExpandDemo
android系统控件拓展

#### 引入依赖

`dependencies {}`中添加配置:
```
implementation 'com.zxn.crease:CreaseViewLib:1.0.2'
implementation 'com.zxn.crease:CreaseViewLib:1.0.0'
```
    
#### 使用:

xml布局中放置控件
```
<com.zxn.expandapp.widget.ExpandCheckBox
    android:id="@+id/cb_checked_all"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="50dp"
    android:layout_marginTop="50dp"
    android:button="@null"
    android:checked="false"
    android:clickable="true"
    android:drawableLeft="@drawable/dl_sc_checked_all_goods"
    android:drawablePadding="@dimen/dp_10"
    android:paddingLeft="@dimen/dp_20"
    android:text="全选"
    android:textColor="@color/c_101010"
    android:textSize="@dimen/sp_12" />
```

代码中的应用:
```
cbCheckedAll.setOnBoxCheckedChangeListener((buttonView, isChecked) -> Toast.makeText(MainActivity.this, "isChecked:" + isChecked, Toast.LENGTH_SHORT).show());
```
#### 效果图

![Image text](/image/view.png)


#### 打标签:

数量增减控件1.0.2:增加复选框控件
```
git tag -a v1.0.2 -m '增加复选框控件'
git push origin v1.0.2
git tag
```
