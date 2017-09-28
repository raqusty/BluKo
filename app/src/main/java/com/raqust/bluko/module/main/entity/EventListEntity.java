package com.raqust.bluko.module.main.entity;

import java.util.List;

/**
 * Created on 2017/9/27.
 * Introduce :
 * Author : zehao
 */

public class EventListEntity {

    private List<EventEntity> list;
    private int remainPage;

    public List<EventEntity> getList() {
        return list;
    }

    public int getRemainPage() {
        return remainPage;
    }
}
