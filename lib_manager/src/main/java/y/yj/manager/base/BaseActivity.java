package y.yj.manager.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.manager.base
 * 描述：      TODO Activity基类
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 07月 20日 17时 52分
 */
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(layoutId());
        ButterKnife.bind(this);
        initView();
    }

    public void initWindow() {
        //todo 默认透明色
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    //todo layout布局文件必备
    public abstract int layoutId();

    //todo 初始化View
    public abstract void initView();

}
