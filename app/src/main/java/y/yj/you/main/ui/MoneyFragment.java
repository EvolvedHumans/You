package y.yj.you.main.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import y.yj.you.R;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.you.main.ui
 * 描述：      TODO
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 06月 06日 17时 27分
 */
public class MoneyFragment extends Fragment {

    private static MoneyFragment moneyFragment;

    public static MoneyFragment getInstance() {
        if (moneyFragment == null) {
            synchronized (MoneyFragment.class) {
                if (moneyFragment == null) {
                    moneyFragment = new MoneyFragment();
                }
            }
        }
        return moneyFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lymain_money, container, false);
    }

}
