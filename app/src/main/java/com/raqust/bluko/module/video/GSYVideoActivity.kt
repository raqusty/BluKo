package com.raqust.bluko.module.video

import android.annotation.TargetApi
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Handler
import android.support.v4.view.ViewCompat
import android.transition.Transition
import android.view.View
import android.widget.ImageView
import butterknife.BindView
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.module.gsyvideoplayer.SampleVideo
import com.raqust.bluko.module.gsyvideoplayer.listener.OnTransitionListener
import com.raqust.bluko.module.gsyvideoplayer.model.SwitchVideoModel
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils


/**
 * Created by linzehao
 * time: 2018/2/20.
 * info:
 */
class GSYVideoActivity : BaseActivity() {

    val IMG_TRANSITION = "IMG_TRANSITION"
    val TRANSITION = "TRANSITION"

    var videoPlayer: SampleVideo? = null

    var orientationUtils: OrientationUtils? = null


    private var isTransition: Boolean = false

    private var transition: Transition? = null

    override fun initViews() {
        videoPlayer = findViewById(R.id.video_player) as  SampleVideo

        val url = "https://res.exexm.com/cw_145225549855002"

        //String url = "http://7xse1z.com1.z0.glb.clouddn.com/1491813192";
        //需要路径的
        //videoPlayer.setUp(url, true, new File(FileUtils.getPath()), "");

        //借用了jjdxm_ijkplayer的URL
        val source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4"
        val name = "普通"
        val switchVideoModel = SwitchVideoModel(name, source1)

        val source2 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4"
        val name2 = "清晰"
        val switchVideoModel2 = SwitchVideoModel(name2, source2)

        val list = mutableListOf<SwitchVideoModel>()
        list.add(switchVideoModel)
        list.add(switchVideoModel2)

        videoPlayer?.setUp(list, true, "测试视频")

        //增加封面
        val imageView = ImageView(this)
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
        imageView.setImageResource(R.mipmap.xxx1)
        videoPlayer?.setThumbImageView(imageView)

        //增加title
        videoPlayer?.getTitleTextView()?.setVisibility(View.VISIBLE)
        //videoPlayer.setShowPauseCover(false);

        //videoPlayer.setSpeed(2f);

        //设置返回键
        videoPlayer?.getBackButton()?.setVisibility(View.VISIBLE)

        //设置旋转
        orientationUtils = OrientationUtils(this, videoPlayer)

        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer?.getFullscreenButton()?.setOnClickListener { orientationUtils?.resolveByClick() }

        //videoPlayer.setBottomProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_progress));
        //videoPlayer.setDialogVolumeProgressBar(getResources().getDrawable(R.drawable.video_new_volume_progress_bg));
        //videoPlayer.setDialogProgressBar(getResources().getDrawable(R.drawable.video_new_progress));
        //videoPlayer.setBottomShowProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_seekbar_progress),
        //getResources().getDrawable(R.drawable.video_new_seekbar_thumb));
        //videoPlayer.setDialogProgressColor(getResources().getColor(R.color.colorAccent), -11);

        //是否可以滑动调整
        videoPlayer?.setIsTouchWiget(true)

        //设置返回按键功能
        videoPlayer?.getBackButton()?.setOnClickListener(View.OnClickListener { onBackPressed() })

        //过渡动画
        initTransition()

    }

    override fun getToolBarResId(): Int = 0

    override fun setListener() {
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_palyer
    }


    override fun onPause() {
        super.onPause()
        videoPlayer?.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        videoPlayer?.onVideoResume()
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    override fun onDestroy() {
        super.onDestroy()
        if (orientationUtils != null)
            orientationUtils?.releaseListener()
    }

    override fun onBackPressed() {
        //先返回正常状态
        if (orientationUtils?.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer?.getFullscreenButton()?.performClick()
            return
        }
        //释放所有
        videoPlayer?.setVideoAllCallBack(null)
        GSYVideoManager.releaseAllVideos()
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressed()
        } else {
            Handler().postDelayed({
                finish()
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
            }, 500)
        }
    }


    private fun initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition()
            ViewCompat.setTransitionName(videoPlayer, IMG_TRANSITION)
            addTransitionListener()
            startPostponedEnterTransition()
        } else {
            videoPlayer?.startPlayLogic()
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun addTransitionListener(): Boolean {
        transition = window.sharedElementEnterTransition
        if (transition != null) {
            transition?.addListener(object : OnTransitionListener() {
                override fun onTransitionEnd(transition: Transition) {
                    super.onTransitionEnd(transition)
                    videoPlayer?.startPlayLogic()
                    transition.removeListener(this)
                }
            })
            return true
        }
        return false
    }


}