package com.raqust.bluko.module.oss;

import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;

import java.io.File;

import butterknife.OnClick;

import static com.raqust.bluko.BuildConfig.API_HOST;

/**
 * Created by zehao on 2017/9/25.
 */

public class OssActivity extends BaseActivity {
    OSS oss;

    @Override
    public void initViews() {
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考访问控制章节
        // 也可查看sample 中 sts 使用方式了解更多(https://github.com/aliyun/aliyun-oss-android-sdk/tree/master/app/src/main/java/com/alibaba/sdk/android/oss/app)
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(
                "STS.CmuUhViCH1bFVG4ERxoqGVMAN"
                , "5zM8ePr1CKUSQYzBzkteX9hvsKAJihGQjkBogXZDVTUu"
                , "CAISkwJ1q6Ft5B2yfSjIoq7AHtLihJxphqCtVGGFoVItY/5ruYjqrDz2IHtEf3lqCOsesfkxnGtV7fkalqB6T55OSAmcNZIoRnCZeODlMeT7oMWQweEuuv/MQBquaXPS2MvVfJ+OLrf0ceusbFbpjzJ6xaCAGxypQ12iN+/m6/Ngdc9FHHP7D1x8CcxROxFppeIDKHLVLozNCBPxhXfKB0ca3WgZgGhku6Ok2Z/euFiMzn+Ck7RP/92pecT5MpI9Z8omAu3YhrImKvDztwdL8AVP+atMi6hJxCzKpNn1ASMKuEXdY7CFqYUwcVEhPfVhRPZe3/H4lOxlvOvIjJjwyBtLMuxTXj7WWIe62szAFfMu0ySdlUo/URqAASyBwxdnwOorgvxqWHrVI41G2mGfagkS2dBuJW50JcbdjrP7jeZmjafemaR4Xx/3dN/h3GCXIVpBLR9y4bRI2Wq5gfpXUYH6gPvcZ9T41kTNV6L+TgUzG6nDPt+BB1FN//D5L9A/QqLOoOqvHfV186YlsHGnfA0T6wRrrwTLVCMj");
        //该配置类如果不设置，会有默认配置，具体可看该类
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);


    }


    @Override
    public void setListener() {

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
        return R.layout.activity_login;
    }

    @Override
    public Toolbar setToolBar() {
        return null;
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6})
    public void Click(View v) {
        switch (v.getId()) {
            case R.id.text1:
                PutObjectRequest put = new PutObjectRequest("xf-gc-test-oss", "123123123", "/storage/sdcard1/DCIM/IMMQY/IMG_20180103204458_606.jpg");
                put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
                    @Override
                    public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                        Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                    }
                });

                OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                    @Override
                    public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                        Log.d("PutObject", "UploadSuccess");
                    }
                    @Override
                    public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                        // 请求异常
                        if (clientExcepion != null) {
                            // 本地异常如网络异常等
                            clientExcepion.printStackTrace();
                        }
                        if (serviceException != null) {
                            // 服务异常
                            Log.e("ErrorCode", serviceException.getErrorCode());
                            Log.e("RequestId", serviceException.getRequestId());
                            Log.e("HostId", serviceException.getHostId());
                            Log.e("RawMessage", serviceException.getRawMessage());
                        }
                    }
                });

                break;
            case R.id.text2:

                String recordDirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/oss_record/";
                File recordDir = new File(recordDirectory);
                // 要保证目录存在，如果不存在则主动创建
                if (!recordDir.exists()) {
                    recordDir.mkdirs();
                }
                // 创建断点上传请求，参数中给出断点记录文件的保存位置，需是一个文件夹的绝对路径
                ResumableUploadRequest request = new ResumableUploadRequest("<bucketName>", "<objectKey>", "<uploadFilePath>", recordDirectory);
                // 设置上传过程回调
                request.setProgressCallback(new OSSProgressCallback<ResumableUploadRequest>() {
                    @Override
                    public void onProgress(ResumableUploadRequest request, long currentSize, long totalSize) {
                        Log.d("resumableUpload", "currentSize: " + currentSize + " totalSize: " + totalSize);
                    }
                });
                request.setDeleteUploadOnCancelling(false);
                OSSAsyncTask resumableTask = oss.asyncResumableUpload(request, new OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult>() {
                    @Override
                    public void onSuccess(ResumableUploadRequest request, ResumableUploadResult result) {
                        Log.d("resumableUpload", "success!");
                    }
                    @Override
                    public void onFailure(ResumableUploadRequest request, ClientException clientExcepion, ServiceException serviceException) {
                        // 异常处理
                    }
                });
                break;
            case R.id.text3:
                break;
            case R.id.text4:
                break;
            case R.id.text5:
                break;
            case R.id.text6:
                break;
            default:
                break;
        }
    }

//    public OssService initOSS(String endpoint, String bucket, UIDisplayer displayer) {
//        OSSCredentialProvider credentialProvider;
//        //使用自己的获取STSToken的类
////从应用服务器控件里面读取应用服务器地址
//        String stsServer = ((EditText) findViewById(R.id.stsserver)).getText().toString();
//        //STSGetter类，封装如何跟从应用服务器取数据，必须继承于OSSFederationCredentialProvider这个类。 取Token这个取决于您所写的APP跟应用服务器数据的协议设计。
//        if (stsServer.equals("")) {
//            credentialProvider = new STSGetter();
//        } else {
//            credentialProvider = new STSGetter(stsServer);
//        }
////获取控件上的bucket名字
//        bucket = ((EditText) findViewById(R.id.bucketname)).getText().toString();
////初始化OSSClient
//        ClientConfiguration conf = new ClientConfiguration();
//        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
//        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
//        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
//        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
//        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider, conf);
//        return new OssService(oss, bucket, displayer);
//    }
}
