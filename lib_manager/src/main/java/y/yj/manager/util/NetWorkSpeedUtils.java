package y.yj.manager.util;

import android.content.Context;
import android.net.TrafficStats;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 项目负责人： 杨帆
 * 包名：      com.yangf.pub_libs.util
 * 描述：      TODO Android 计算手机下载当前文件网速的工具类，
 * 实现思路：
 * 一、Android提供有获取当前总流量的方法
 * 二、上一秒减去下一面的流量差即是网速
 * 三、计算
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 03月 22日 14时 32分
 */
public class NetWorkSpeedUtils {

    private final static String TAG = "com.yangf.pub_libs.util.NetWorkSpeedUtils";

    private final Context context;
    private final Timer timer;

    private long lastTotalRxBytes = 0;
    private long lastTimeStamp = 0;

    private long speed1 = 0;
    private long speed2 = 0;

    public NetWorkSpeedUtils(Context context) {
        this.context = context;
        timer = new Timer();
    }

    /**
     * 获取实时网速
     */
    public String getRealTimeNetwork(){
        return StringUtil.toString(speed1)+"."+StringUtil.toString(speed2)+"kb/s";
    }

    /**
     * TrafficStats.getUidRxBytes(uid) 获取某个网络UID接收字节的总和
     * TrafficStats.getUidTxBytes(uid) 获取某个网络UID发送字节的总和
     *
     * @return 获取当前接收字节的总和
     */
    private long getTotalRxBytes() {
        return TrafficStats.getUidRxBytes(context.getApplicationInfo().uid) ==
                TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalRxBytes() / 1024);//转为KB
    }

    /**
     * @return 获取当前当前网速
     */
    private void showNetSpeed() {
        //当前接收字节的速率
        long nowTotalRxBytes = getTotalRxBytes();
        long nowTimeStamp = System.currentTimeMillis();

        speed1 = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 / (nowTimeStamp - lastTimeStamp));//毫秒转换
        speed2 = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 % (nowTimeStamp - lastTimeStamp));//毫秒转换

        lastTimeStamp = nowTimeStamp;
        lastTotalRxBytes = nowTotalRxBytes;
    }


    /**
     * 开始计算网速
     */
    public void startShowNetSpeed(){
        lastTotalRxBytes = getTotalRxBytes();
        lastTimeStamp = System.currentTimeMillis();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                showNetSpeed();
            }
        },1000,1000); //1s后启动任务，每1秒钟执行一次任务
    }

    /**
     * 停止计算网速
     */
    public void endShowNetSpeed(){
        timer.cancel();
    }

}





















