package y.yj.you.main.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.api.RefreshLayout;

import y.yj.you.R;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.you.main.ui
 * 描述：      TODO
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 06月 06日 17时 27分
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "y.yj.you.main.ui.HomeFragment";
    private static HomeFragment homeFragment;

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            synchronized (HomeFragment.class) {
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
            }
        }
        return homeFragment;
    }

    @SuppressLint("ResourceAsColor")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lymain_home, container, false);
        RefreshLayout refreshLayout = view.findViewById(R.id.refresh);
        //todo 上拉刷新监听
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            refreshLayout1.finishRefresh(1000, true, false);
        });
        //todo 底部加载监听
        refreshLayout.setOnLoadMoreListener(refreshLayout12 -> {
            refreshLayout12.finishLoadMore(1000, true, false);
        });
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setAdapter(new HomeAdapter());
        return view;
    }

}
