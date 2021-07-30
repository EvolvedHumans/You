package y.yj.module_login;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import y.yj.lib_weight.ScreenVideoView;
import y.yj.manager.argument.ArguActivity;
import y.yj.manager.base.BaseActivity;

@Route(path = ArguActivity.ARouterFlashActivity)
public class FlashActivity extends BaseActivity {

    ScreenVideoView videoView;

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
        videoView = findViewById(R.id.video);
        videoView.setVideoURI(Uri.parse("android.resource://y.yj.module_login/" + R.raw.login));
        mediaController = new MediaController(this);
        mediaController.setVisibility(View.INVISIBLE);
        videoView.setMediaController(mediaController);
        videoView.setOnCompletionListener(mp -> {
            //todo 跳转到登录界面中
            ARouter.getInstance().build(ArguActivity.ARouterLoginActivity).navigation();
            finish();
        });
        textView = findViewById(R.id.text);
        textView.setOnClickListener(v -> {
            //todo 跳转到登录界面中
            ARouter.getInstance().build(ArguActivity.ARouterLoginActivity).navigation();
            finish();
        });
    }

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