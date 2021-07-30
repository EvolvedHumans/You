package y.yj.lib_weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.lib_weight
 * 描述：      TODO 全屏播放器
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 07月 18日 12时 21分
 */
public class ScreenVideoView extends VideoView {


    public ScreenVideoView(Context context) {
        super(context);
    }

    public ScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 在VideoView宽高都设置为match_parent后，由于视频源的尺寸导致播放的时候不能全屏，只需要重写VideoView的onMeasure方法：
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

}
