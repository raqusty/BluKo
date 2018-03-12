package com.raqust.bluko.module.log

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.bumptech.glide.request.target.Target
import java.util.*
import android.os.AsyncTask
import com.raqust.bluko.common.utils.PathUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:
 */
class LogActivity : BaseActivity() {


    internal var TAG = "linzehao"

    var list = mutableListOf<String>()

    @BindView(R.id.myimage)
    internal var myImage: ImageView? = null


    override fun initViews() {
    }

    override fun setListener() {

    }

    override fun getToolBarResId(): Int {
        return 0
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager) {

    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_login
    }

    var index = 0

    fun click(v: View) {
        when (v.id) {
            R.id.text1 -> {

                LogManager.logClickAction(" " +index ++ )
                LogManager.logClickAction(" " +index ++)
                LogManager.logClickAction(" " +index ++)
            }
            R.id.text2 -> {
                for (i in 0 until 39) {
                    LogManager.logClickAction("" + i)
                }

            }
            R.id.text3 -> {
                LogManager.logStartTimeAction("11")
                LogManager.logStartTimeAction("22")
                LogManager.logStartTimeAction("33")
            }
            R.id.text4 -> {
                for (i in 0 until 10) {
                    LogManager.logStartSlideAction("" + index++)
                    if (i == 9){
                        LogManager.logStopSlideAction("" + index++)
                    }
                }

            }
            R.id.text5 -> {
                val offset = TimeZone.getDefault().rawOffset
                val tz = "${if (offset > 0) "+" else "-"}${Math.abs(offset) / 1000 / 3600}"
                Log.d("tttzz", tz)
            }
            R.id.text6 -> {
                var sss = this.findViewById(R.id.myimage) as ImageView
                Glide.with(this).load("http://xf-gc-test-oss.oss-cn-hangzhou.aliyuncs.com/jpg/201803/10/152066567150638638.jpg")
                        .asBitmap()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(sss);

                GetImageCacheTask(this).execute("http://xf-gc-test-oss.oss-cn-hangzhou.aliyuncs.com/jpg/201803/10/152066567150638638.jpg");
            }
            else -> {
            }
        }
    }


    internal inner class GetImageCacheTask(private val context: Context) : AsyncTask<String, Void, File>() {

        override fun doInBackground(vararg params: String): File? {
            val imgUrl = params[0]
            try {
                return Glide.with(context)
                        .load(imgUrl)
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get()
            } catch (ex: Exception) {
                return null
            }

        }

        override fun onPostExecute(result: File?) {
            if (result == null) {
                return
            }
            //此path就是对应文件的缓存路径
            val path = result!!.getPath()
            //将缓存文件copy, 命名为图片格式文件
            Log.i("linzehao","11   "+path)
//            PathUtils.copyFile(path, newPath)
        }
    }




}
