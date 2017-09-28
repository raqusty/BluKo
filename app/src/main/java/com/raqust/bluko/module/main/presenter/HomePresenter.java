package com.raqust.bluko.module.main.presenter;

import com.raqust.bluko.common.net.CallBack.JsonResponseCallBack;
import com.raqust.bluko.module.main.entity.EventListEntity;
import com.raqust.bluko.module.main.model.HomeModel;
import com.raqust.bluko.module.main.model.IHomeModel;
import com.raqust.bluko.module.main.view.IHomeView;

/**
 * Created on 2017/9/28.
 * Introduce :
 * Author : zehao
 */

public class HomePresenter {

    private IHomeView mView;
    private IHomeModel mModel;

    public HomePresenter(IHomeView view) {
        mView = view;
        mModel = new HomeModel();
    }

    public void getCommentList(String eventId, int page) {
        mModel.getDataList(eventId, page, new JsonResponseCallBack<EventListEntity>(EventListEntity.class) {

            @Override
            public void onSuccess(String code, String msg, String data, EventListEntity responseResult) {
                mView.setCommentList(responseResult);
            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onFail(String errorCode, String message, String result) {

            }
        });
    }
}
