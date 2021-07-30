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
 * 创建时间：  2021年 06月 06日 17时 28分
 */
public class MessageFragment extends Fragment {

    private static MessageFragment messageFragment;

    public static MessageFragment getInstance() {
        if (messageFragment == null) {
            synchronized (MessageFragment.class) {
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                }
            }
        }
        return messageFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lymain_message, container, false);
    }

}
