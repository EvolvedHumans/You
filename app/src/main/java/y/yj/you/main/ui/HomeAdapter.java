package y.yj.you.main.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import y.yj.manager.argument.ArguActivity;
import y.yj.manager.log.Log4j;
import y.yj.you.R;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.you.main.ui
 * 描述：      TODO 应用栏，需要自定义行距
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 07月 17日 22时 48分
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.VH> {

    private static final String TAG = "y.yj.you.main.ui.HomeAdapter";

    //定义一个内部类viewHolder，继承自RecyclerView.ViewHolde，用来缓存子项的各个实例，提高刷新效率
    public static class VH extends RecyclerView.ViewHolder {

        //定义一个内部类viewHolder，继承自RecyclerView.ViewHolde，用来缓存子项的各个实例，提高刷新效率
        ImageButton imageButton;
        TextView textView;

        public VH(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.image_icon);
            textView = itemView.findViewById(R.id.text_icon);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.apply, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.imageButton.setBackgroundResource(R.drawable.apply_item_motion);
        holder.textView.setText("派健康");
        holder.imageButton.setOnClickListener(v -> {

            //todo 跳转到app_health下的登录模块
            Log4j.d(TAG,"跳转到app_health下的登录模块");

            ARouter.getInstance()
                    .build(ArguActivity.ARouterFlashActivity)
                    .navigation();
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
