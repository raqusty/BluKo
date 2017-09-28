package com.raqust.bluko.module.main.model;

import com.raqust.bluko.common.net.CallBack.JsonResponseCallBack;
import com.raqust.bluko.module.main.entity.EventListEntity;

/**
 * Created on 2017/9/27.
 * Introduce :
 * Author : zehao
 */

public interface IHomeModel {
    public void getDataList(String eventId, int  page ,JsonResponseCallBack<EventListEntity> callback);
}
