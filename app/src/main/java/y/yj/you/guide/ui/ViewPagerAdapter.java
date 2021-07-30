package y.yj.you.guide.ui;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.you.guide
 * 描述：      TODO
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 05月 30日 20时 20分
 */
public class ViewPagerAdapter extends PagerAdapter {

    List<View> viewList;

    public ViewPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, int position) {
        View view1 = viewList.get(position);
        view.addView(view1);
        return view1;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup view, int position, @NonNull Object object) {
        view.removeView(viewList.get(position));
    }
}
