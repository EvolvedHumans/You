package y.yj.lib_weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.weight
 * 描述：      TODO 自定义按压效果图片，当按下后，透明度设置为0.5f
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 06月 06日 10时 07分
 */
public class PresssImageView extends androidx.appcompat.widget.AppCompatImageView {

    private static final String TAG = "y.yj.weight.PresssImageView";

    public PresssImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            setAlpha(0.5f);
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE){
            setAlpha(0.5f);
        }
        else {
            setAlpha(1.0f);
        }
        return true;
    }
}
