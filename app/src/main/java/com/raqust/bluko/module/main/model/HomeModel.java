package com.raqust.bluko.module.main.model;


import com.raqust.bluko.common.net.CallBack.JsonResponseCallBack;
import com.raqust.bluko.common.net.NetUtils;
import com.raqust.bluko.module.main.entity.EventListEntity;

import java.util.HashMap;

/**
 * Created on 2017/9/28.
 * Introduce :
 * Author : zehao
 */

public class HomeModel implements IHomeModel {
    @Override
    public void getDataList(String eventId, int page, JsonResponseCallBack<EventListEntity> callback) {
        HashMap<String, String> strParams = new HashMap<String, String>();
        strParams.put("all", eventId);
        strParams.put("page", page + "");
        strParams.put("pageSize", "30");
        NetUtils.requestGetString("http://app.lamian.tv/api/Lamian_v3/recommend/video", strParams, callback);
    }
}
