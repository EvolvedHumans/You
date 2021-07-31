package y.yj.module_login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import butterknife.BindView;
import y.yj.lib_weight.ScreenVideoView;
import y.yj.manager.argument.ArguActivity;
import y.yj.manager.base.BaseActivity;
import y.yj.manager.log.Log4j;

@Route(path = ArguActivity.ARouterFlashActivity)
public class FlashActivity extends BaseActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.video)
    ScreenVideoView videoView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.text)
    TextView textView;

    MediaController mediaController;

    @Override
    public void initWindow() {
        //todo 顶部栏问题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_flash;
    }

    @Override
    public void initView() {
        videoView.setVideoURI(Uri.parse("android.resource://y.yj.module_login/" + R.raw.login));
        mediaController = new MediaController(this);
        mediaController.setVisibility(View.INVISIBLE);
        videoView.setMediaController(mediaController);
        videoView.setOnCompletionListener(completionListener);
        textView.setOnClickListener(onClickListener);
        test();
    }

    public void test(){
//        //创建集合
//        Collection collection = new ArrayList();
//        collection.add("菜单");
//        collection.add("西瓜");
//        System.out.println(collection.size());
//        System.out.println(collection);
//
//        //使用迭代器（迭代器专门用来遍历集合的一种方式）
//        //1.hasNext() 有没有下一个元素
//        //2.next() 获取下一个元素
//        //3.remove() 删除当前元素
////        Iterator iterator = collection.iterator();
////        //迭代器遍历
////        while (iterator.hasNext()){
////            System.out.println(iterator.next());
////        }
//
//        //Collection遍历
//        for (Object obj:collection){
//            System.out.println(obj);
//        }
        List<String> list = new LinkedList<>();
        list.add("西瓜");
        list.add("可乐");

        System.out.println(list.toString());
        System.out.println("集合个数："+list.size());

        //循环遍历
        System.out.println("----- 1.for遍历 -----");
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

        System.out.println("----- 2.foreach遍历 -----");
        for (String s : list){
            System.out.println(s);
        }

        System.out.println("----- 3.使用迭代器 ------");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //使用列表迭代器，和Iterator的区别，ListIterator可以向前或向后遍历，添加、删除、修改元素
        System.out.println("----- 4.使用列表迭代器 ------");
        ListIterator listIterator = list.listIterator();
        System.out.println("----- 正序遍历 ------");
        //正序遍历
        while (listIterator.hasNext()){
            System.out.println(listIterator.nextIndex()+"："+listIterator.next());
        }
        System.out.println("----- 反向遍历 ------");
        //反向遍历
        while (listIterator.hasPrevious()){
            System.out.println(listIterator.previousIndex()+"："+listIterator.previous());
        }

    }

    MediaPlayer.OnCompletionListener completionListener = mp -> {
        //todo 跳转到登录界面中
        ARouter.getInstance().build(ArguActivity.ARouterLoginActivity).navigation();
    };

    View.OnClickListener onClickListener = v -> {
        //todo 跳转到登录界面中
        ARouter.getInstance().build(ArguActivity.ARouterLoginActivity).navigation();
    };

    @Override
    protected void onStart() {
        super.onStart();
        videoView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoView.stopPlayback();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}