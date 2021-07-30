package y.yj.you.main;

import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import y.yj.manager.argument.ArguActivity;
import y.yj.manager.base.BaseActivity;
import y.yj.you.main.ui.HomeFragment;
import y.yj.you.main.ui.MeFragment;
import y.yj.you.main.ui.MessageFragment;
import y.yj.you.main.ui.MoneyFragment;
import y.yj.you.R;

@Route(path = ArguActivity.ARouterMainActivity)
public class MainActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment, HomeFragment.getInstance())
                .commit();//上一个代码区的容器

        //不使用图标默认变色
        BottomNavigationView bottomNavigationView = findViewById(R.id.bv_home_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.item1) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment, HomeFragment.getInstance())
                        .commit();//上一个代码区的容器
            }
            if (item.getItemId() == R.id.item2) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment, MoneyFragment.getInstance())
                        .commit();//上一个代码区的容器
            }
            if (item.getItemId() == R.id.item3) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment, MessageFragment.getInstance())
                        .commit();//上一个代码区的容器
            }
            if (item.getItemId() == R.id.item4) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment, MeFragment.getInstance())
                        .commit();//上一个代码区的容器
            }
            return true;
        });
    }

}