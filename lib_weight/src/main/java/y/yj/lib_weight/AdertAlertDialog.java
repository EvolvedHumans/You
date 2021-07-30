package y.yj.lib_weight;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;;

import androidx.annotation.NonNull;

import com.google.android.material.tabs.TabLayout;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.lib_weight
 * 描述：      TODO 广告Dialog，自定义广告资源
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 07月 19日 01时 20分
 */
public class AdertAlertDialog extends Dialog {

    @SuppressLint("StaticFieldLeak")
    public static volatile AdertAlertDialog adertAlertDialog;


    public synchronized static void init(Context context) {
        if (adertAlertDialog == null) {
            synchronized (AdertAlertDialog.class){
                if (adertAlertDialog == null){
                    adertAlertDialog = new AdertAlertDialog(context);
                }
                if (!adertAlertDialog.isShowing()){
                    adertAlertDialog.show();
                }
            }
        }
    }

    private final ImageView imageView;

    public AdertAlertDialog(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.advert_dialog, null);
        setCancelable(false); //设置点击失效，
        setContentView(view);
        Window window = getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawableResource(R.color.touming);
        imageView = window.findViewById(R.id.icon);
        TextView textView = window.findViewById(R.id.bottom);
        textView.setOnClickListener(v -> {
            if (isShowing()){
                cancel();
            }
        });
    }

    /**
     * 若无，则弹出默认图片
     *
     * @param resId
     */
    public void setBackground(int resId) {
        imageView.setBackgroundResource(resId);
    }
}
