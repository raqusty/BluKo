package com.raqust.bluko.module.jpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;
import com.raqust.bluko.common.event.DataSynEvent;
import com.raqust.bluko.common.event.MessageEvent;
import com.raqust.bluko.module.ChooseView.ChooseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by linzehao
 * time: 2018/1/24.
 * info:
 */

public class PushActivity extends BaseActivity {

    @BindView(R.id.text1)
    TextView QQLogin;

    @BindView(R.id.text2)
    TextView WeiBoLogin;

    @BindView(R.id.text3)
    TextView WeiXinLogin;

    @BindView(R.id.text4)
    TextView QQShare;

    @BindView(R.id.text5)
    TextView WeiBoShare;

    @BindView(R.id.text6)
    TextView WeiXinShare;

    String TAG = "linzehao";

    @Override
    public void initViews() {
        mEventKey = true;
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

    private Boolean isKey = false;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_login;
    }


    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6})
    public void Click(View v) {
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this,ChooseActivity.class);

        switch (v.getId()) {
            case R.id.text1:
                //获取NotificationManager实例

                //实例化NotificationCompat.Builde并设置相关属性
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        //设置小图标
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //设置通知标题
                        .setContentTitle("最简单的Notification")
                        //设置通知内容
                        .setContentText("只有小图标、标题、内容戊二醛翁热气二群翁热气球内容戊二醛翁热气二群翁热气球内容戊二醛翁热气二群翁热气球内容戊二醛翁热气二群翁热气球内容戊二醛翁热气二群翁热气球内容戊二醛翁热气二群翁热气球内容戊二醛翁热气二群翁热气球");
                //设置通知时间，默认为系统发出通知的时间，通常不用设置
//                .setWhen(System.currentTimeMillis());
                //通过builder.build()方法生成Notification对象,并发送通知,id=1
                notifyManager.notify(1, builder.build());
                break;
            case R.id.text2:

                RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_notification);
                remoteViews.setTextViewText(R.id.layout_app,"helloGame");
                remoteViews.setTextViewText(R.id.layout_text,"我是谁");
                remoteViews.setImageViewResource(R.id.layout_image,R.mipmap.ic_launcher_round);
                remoteViews.setTextViewText(R.id.layout_text1,"第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知第一条通知");
                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//                remoteViews.setOnClickPendingIntent(R.id.layout_text,pendingIntent);

                NotificationManager notifyManager1 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)
                        .setContentTitle("我是谁")
                        .setContentText("第一条通知第一条通知第一条通知第一条通知")
                        .setCustomBigContentView(remoteViews);
                notifyManager1.notify(2, builder1.build());


                break;
            case R.id.text3:

                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this);
                builder2.setContentTitle("横幅通知");
                builder2.setContentText("请在设置通知管理中开启消息横幅提醒权限");
                builder2.setDefaults(NotificationCompat.DEFAULT_ALL);
                builder2.setSmallIcon(R.mipmap.ic_launcher);
                PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
                builder2.setContentIntent(pIntent);
                //这句是重点
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder2.setVisibility(Notification.VISIBILITY_PUBLIC);
                    // 关联PendingIntent
                    builder2.setFullScreenIntent(pIntent, true);// 横幅
                }
                builder2.setAutoCancel(true);
                Notification notification = builder2.build();
                notifyManager.notify(123,notification);
                break;
            case R.id.text4:
                break;
            case R.id.text5:
                DataSynEvent a = new DataSynEvent();
                a.setCount(234234);

                EventBus.getDefault().post(a);
                break;
            case R.id.text6:
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DataSynEvent event) {
//        QQShare.setText(event.getCount()+"");
        QQShare.setText(""+event.getCount());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
//        QQShare.setText(event.getCount()+"");
        WeiBoShare.setText(event.getMsgCount() +"  "+event.getMsgType() );
    }

}
