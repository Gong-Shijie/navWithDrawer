package com.example.navdrawer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
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
