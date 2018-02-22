package com.raqust.bluko.module.Gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;
import com.yalantis.ucrop.model.AspectRatio;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.ui.RxGalleryListener;
import cn.finalteam.rxgalleryfinal.ui.base.IRadioImageCheckedListener;

/**
 * Created by linzehao
 * time: 2017/12/20.
 * info:
 */

public class GalleryFinal extends BaseActivity {
    @BindView(R.id.result)
    TextView textView;
    private List<MediaBean> list = null;

    @BindView(R.id.imageview)
    ImageView imageview;

    @Override
    public void initViews() {
        Log.i("linzehao",this.getCacheDir().toString());
        File cache = new File(this.getCacheDir() + "/crop");
        if (!cache.exists()) cache.mkdirs();
        RxGalleryFinalApi.setImgSaveRxDir(cache);
        RxGalleryFinalApi.setImgSaveRxCropDir(cache);

        Log.i("linzehao","123   "+RxGalleryFinalApi.getImgSaveRxCropDirByStr());
    }

    @Override
    public void setListener() {
        //裁剪图片的回调
        RxGalleryListener
                .getInstance()
                .setRadioImageCheckedListener(new IRadioImageCheckedListener() {
                    @Override
                    public void cropAfter(Object t) {
                        Log.i("linzehao",t.toString());
                    }

                    @Override
                    public boolean isActivityFinish() {
                        return true;
                    }
                }
        );
    }

    @Override
    public int getToolBarResId() {
        return 0;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_gallery;
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.text1: {
                RxGalleryFinal
                        .with(GalleryFinal.this)
                        .image()
                        .radio()
                        .cropAspectRatioOptions(0, new AspectRatio("2:3", 30, 10))

                        .crop()
                        .imageLoader(ImageLoaderType.GLIDE)
                        .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                Toast.makeText(GalleryFinal.this, "选中了图片路径：" + imageRadioResultEvent.getResult().getOriginalPath(), Toast.LENGTH_SHORT).show();
                                textView.setText(imageRadioResultEvent.getResult().getOriginalPath());
                            }
                        })
                        .openGallery();
                break;
            }
            case R.id.text2: {
                RxGalleryFinal rxGalleryFinal = RxGalleryFinal
                        .with(GalleryFinal.this)
                        .image()
                        .multiple();
                if (list != null && !list.isEmpty()) {
                    rxGalleryFinal.selected(list);
                }
                rxGalleryFinal.maxSize(8)
                        .imageLoader(ImageLoaderType.GLIDE)
                        .subscribe(new RxBusResultDisposable<ImageMultipleResultEvent>() {

                            @Override
                            protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                list = imageMultipleResultEvent.getResult();
                                String str = "";
                                for (MediaBean bean : list) {
                                    Log.i("linzehao", bean.getOriginalPath());
                                    str = str + bean.getOriginalPath() + "/r/n";
//                                    Uri uri = Uri.parse(bean.getOriginalPath());
                                    Bitmap bm = BitmapFactory.decodeFile(bean.getOriginalPath());
                                    imageview.setImageBitmap(bm);
                                }
                                textView.setText(str);
                                Toast.makeText(getBaseContext(), "已选择" + imageMultipleResultEvent.getResult().size() + "张图片", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                super.onComplete();
                                Toast.makeText(getBaseContext(), "OVER", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .openGallery();
                break;
            }
            case R.id.text3: {
//                RxGalleryFinalApi
//                        .getInstance(GalleryFinal.this)
//                        .setType(RxGalleryFinalApi.SelectRXType.TYPE_VIDEO, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_RADIO)
//                        .setVDRadioResultEvent(new RxBusResultDisposable<ImageRadioResultEvent>() {
//                            @Override
//                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
//                                Toast.makeText(getApplicationContext(), imageRadioResultEvent.getResult().getOriginalPath(), Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .open();

                RxGalleryFinal.with(GalleryFinal.this)
                        .radio()//单选
                        .video()//视频
                        .imageLoader(ImageLoaderType.GLIDE)
                        //里面可以选择主流图片工具  PICASSO  GLIDE  FRESCO UNIVERSAL(ImageLoader)
                        .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                Toast.makeText(getApplicationContext(), imageRadioResultEvent.getResult().getOriginalPath(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .openGallery();
                break;
            }
        }

    }
}
