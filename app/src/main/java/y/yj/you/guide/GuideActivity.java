package y.yj.you.guide;

import androidx.viewpager.widget.ViewPager;

import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.LinkedList;
import java.util.List;

import y.yj.manager.argument.ArguActivity;
import y.yj.manager.base.BaseActivity;
import y.yj.manager.log.Log4j;
import y.yj.you.R;
import y.yj.you.guide.ui.ViewPagerAdapter;

public class GuideActivity extends BaseActivity {

    private static final String TAG = "y.yj.you.guide.GuideActivity";

    @Override
    public int layoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        ViewPager viewPager = findViewById(R.id.pagers);
        viewPager.setAdapter(new ViewPagerAdapter(addView()));
    }

    public List<View> addView() {
        List<View> views = new LinkedList<>();
        views.add(View.inflate(this, R.layout.item_ly, null));
        views.add(View.inflate(this, R.layout.item_ly1ydy, null));
        views.add(View.inflate(this, R.layout.item_ly2ydy, null));
        View view = View.inflate(this, R.layout.item_ly3ydy, null);
        view.findViewById(R.id.next).setOnClickListener(v -> {
            //todo 跳转到主页
            ARouter.getInstance()
                    .build(ArguActivity.ARouterMainActivity)
                    .navigation();
        });
        views.add(view);
        return views;
    }

}