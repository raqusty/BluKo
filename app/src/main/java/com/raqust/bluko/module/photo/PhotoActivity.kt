package com.raqust.bluko.module.photo

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.PointF
import android.net.Uri
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.ImageViewState
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.*
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import uk.co.senab.photoview.PhotoView
import java.io.File


/**
 * Created by linzehao
 * time: 2018/2/20.
 * info:
 */
class PhotoActivity : BaseActivity() {
    override fun initViews() {
//        photoView.setImage()
//
//        Glide.with(mContext)
//                .load("http://xf-gc-test-oss.oss-cn-hangzhou.aliyuncs.com/jpg/201802/12/151842226050379936.jpg")
////                .centerCrop()
//                .skipMemoryCache(true)
//                .into(photoView)

        initView()
    }

    override fun getToolBarResId(): Int {
        return 0
    }


    override fun setListener() {
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {
    }

    override fun getContentViewResId(): Int = R.layout.activity_photo

    fun initView() {
        val imagePath = "http://xf-gc-prod-oss.oss-cn-hangzhou.aliyuncs.com/png/201802/13/152094614136265318061.png"
//        val imagePath = "http://xf-gc-test-oss.oss-cn-hangzhou.aliyuncs.com/jpg/201802/12/151844919480179657.jpg"


        val photoView = findViewById(R.id.photo_view) as PhotoView
        Glide.with(mContext)
                .load(imagePath)
//                .centerCrop()
                .skipMemoryCache(true)
                .into(photoView)

//        val imageView = findViewById(R.id.photo_view1) as SubsamplingScaleImageView
        //手势回调
//        val gestureDetector = GestureDetector(this, object : SimpleOnGestureListener() {
//            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
//                if (imageView.isReady) {
//                    val sCoord = imageView.viewToSourceCoord(e.x, e.y)
//                    Toast.makeText(applicationContext, "单击: " + sCoord.x.toInt() + ", " + sCoord.y.toInt(),
//                            Toast.LENGTH_SHORT).show()
//                } else {
//                    // Toast.makeText(getApplicationContext(), fail_tips, Toast.LENGTH_SHORT).show();
//                }
//
//                return false
//            }
//
//            override fun onLongPress(e: MotionEvent) {
//                if (imageView.isReady) {
//                    val sCoord = imageView.viewToSourceCoord(e.x, e.y)
//                    Toast.makeText(applicationContext, "长按: " + sCoord.x.toInt() + ", " + sCoord.y.toInt(),
//                            Toast.LENGTH_SHORT).show()
//                } else {
//                    //Toast.makeText(getApplicationContext(), fail_tips, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            override fun onDoubleTap(e: MotionEvent): Boolean {
//                if (imageView.isReady) {
//                    val sCoord = imageView.viewToSourceCoord(e.x, e.y)
//                    Toast.makeText(applicationContext, "双击: " + sCoord.x.toInt() + ", " + sCoord.y.toInt(),
//                            Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT).show()
//                }
//
//                return false
//            }
//        })

//        imageView.setOnTouchListener { view, motionEvent -> gestureDetector.onTouchEvent(motionEvent) }

//        imageView.maxScale = 15f
//        imageView.isZoomEnabled = true
        // spinner.setVisibility(View.GONE);
//        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM)
//        Glide.with(mContext)
//                .load(imagePath).downloadOnly(object : SimpleTarget<File>() {
//            override fun onResourceReady(resource: File, glideAnimation: GlideAnimation<in File>) {
//                // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
//                val imageSource = ImageSource.uri(Uri.fromFile(resource))
//                val sWidth = BitmapFactory.decodeFile(resource.absolutePath).width
//                val sHeight = BitmapFactory.decodeFile(resource.absolutePath).height
//                val wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//                val height = wm.defaultDisplay.height
//                if (sHeight >= height && sHeight / sWidth >= 3) {
//                    imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP)
//                    imageView.setImage(imageSource, ImageViewState(0f, PointF(0f, 0f), 0))//
//                    imageView.setDoubleTapZoomStyle(ZOOM_FOCUS_CENTER)
//                } else {
//                    imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM)
//                    imageView.setImage(imageSource)
//                    imageView.setDoubleTapZoomStyle(ZOOM_FOCUS_CENTER)
//                }
//            }
//        })
    }


}