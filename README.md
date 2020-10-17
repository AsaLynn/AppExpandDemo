# AppExpandDemo
android系统控件拓展

#### 引入依赖

`dependencies {}`中添加配置:
```
implementation 'com.zxn.widget:expand-widget:1.1.9'
```
    
#### 使用:
1,带图标和清除按钮的`EditText`
```
<com.zxn.widget.ExpandEditText
    android:id="@+id/search_friend_edit"
    android:layout_width="@dimen/dp_0"
    android:layout_height="@dimen/dp_40"
    android:layout_marginTop="@dimen/dp_15"
    android:layout_marginRight="@dimen/dp_15"
    android:background="@drawable/sp_bg_rec_r20_solid_ff1b1c27"
    android:hint="输入ID或者昵称搜索好友"
    android:inputType="text"
    android:paddingLeft="@dimen/dp_20"
    android:paddingRight="@dimen/dp_20"
    android:textColor="@color/c_ffffff"
    android:textColorHint="@color/c_6a6a86"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toLeftOf="@id/tv_cancel_search"
    app:layout_constraintTop_toTopOf="parent" />
```

xml布局中放置控件
```
<com.zxn.widget.ExpandCheckBox
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
![Image text](/image/view1.png)
![Image text](/image/image_edit.png)


#### 打标签:

系统控件拓展1.1.9:增加Toolbar
```
git tag -a v1.1.9 -m '增加Toolbar'
git push origin v1.1.9
git tag
```

系统控件拓展1.1.8:升级输入框
```
git tag -a v1.1.8 -m '升级输入框ExpandEditText'
git push origin v1.1.8
git tag
```

系统控件拓展1.1.3:增加开关控件
```
git tag -a v1.1.3 -m '增加弹性的ScrollView'
git push origin v1.1.3
git tag
```

系统控件拓展1.1.2:增加开关控件
```
git tag -a v1.1.2 -m '增加开关控件'
git push origin v1.1.2
git tag
```

系统控件拓展1.1.1:增加开关控件
```
git tag -a v1.1.1 -m '增加开关控件'
git push origin v1.1.1
git tag
```

