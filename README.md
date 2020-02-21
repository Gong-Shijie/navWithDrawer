# navWithDrawer


![开始使用Navigation](https://upload-images.jianshu.io/upload_images/19741117-63b5dfc0ce4bc0f1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)    

demo:[https://github.com/Gong-Shijie/navWithDrawer](https://github.com/Gong-Shijie/navWithDrawer)


### 应用框架  

![Android studio直接创建](https://upload-images.jianshu.io/upload_images/19741117-adca2c98c887c9b5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
Google建议开发者只使用一个Activity搭配Fragment来架构程序  
可以直接new一个Navigation Drawer的项目  

![](https://upload-images.jianshu.io/upload_images/19741117-a9bdd3337818976f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
进一步调整为需要的效果  
*****

#### Drawer菜单  
首先要了解菜单Menu，菜单在Android部件系统中有三种类型   
![Drawer Menu](https://upload-images.jianshu.io/upload_images/19741117-3f7e28c749552523.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
![Toolbar菜单](https://upload-images.jianshu.io/upload_images/19741117-fe2af1029bcb1c53.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![底部菜单](https://upload-images.jianshu.io/upload_images/19741117-582a7993568eda97.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
对于这些菜单我们都可以通过他们来进行页面导航切换  
使用方法是直接在创建项目的时候使用提供的模板  
  
![](https://upload-images.jianshu.io/upload_images/19741117-d3db3628b529dee6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
根据这些默认模板可以减少项目构建的封锁步骤   
viewmodel也可以勾选省去创建ViewModel步骤  
*****
#### 创建一个Navigation Drawer 应用  
![MainXML](https://upload-images.jianshu.io/upload_images/19741117-b1f93adf08f380a0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

重写方法实现对所有菜单监听  
![处理toolbar菜单监听](https://upload-images.jianshu.io/upload_images/19741117-2c5ce9403972d015.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
  //只要是菜单选项被点击都会调用该方法
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
               Toast.makeText(this, "Click !", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settidfngs:
               Toast.makeText(this, "Click ! ", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
```
动态修改和设置抽屉菜单  
![drawer菜单](https://upload-images.jianshu.io/upload_images/19741117-6ac7ecf66f426727.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
   //动态添加menuItem项设置id
        Menu menu = navigationView.getMenu();
        menu.clear();
        menu.add(0, R.id.nav_gallery, 0, "gallery").
setIcon(R.drawable.ic_menu_gallery);
        menu.add(0, R.id.nav_send, 1, "send").
setIcon(R.drawable.ic_menu_send);
//设置点击监听但是不要消耗点击事件否则影响导航
  menu.getItem(0).setOnMenuItemClickListener();
```   
 *****
#### Navigation  
![](https://upload-images.jianshu.io/upload_images/19741117-79c6c96892eab3b1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
*****
#### 主要代码  

MainActivity.java  
```
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //实现toolbar替换默认的Actionbar
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


//将NavController 和app bar这种menu绑定从而实现Fragment切换的管理
//        也可以和其他的menu类型绑定
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                navController.getGraph()
        )
                .setDrawerLayout(drawer)
                .build();

        NavigationUI.setupActionBarWithNavController(this,
                navController, mAppBarConfiguration);

        //使得drawer显示
        NavigationUI.setupWithNavController(navigationView,
                navController);

        //动态添加menuItem项设置id
        Menu menu = navigationView.getMenu();

        menu.clear();
        menu.add(0, R.id.nav_gallery, 0, "gallery").setIcon(R.drawable.ic_menu_gallery);
        menu.add(0, R.id.nav_send, 1, "send").setIcon(R.drawable.ic_menu_send);

    }

    //设置actionbar中的菜单显示
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //只要是菜单选项被点击都会调用该方法
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Click !", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settidfngs:
                Toast.makeText(this, "Click ! ", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //处理左上角的菜单被点击的时候的回调
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
```
*****  

activity_main.xml  
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start">

    <include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" />

</androidx.drawerlayout.widget.DrawerLayout>
```
*****  
HomeFragment.java  
```
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
```  






