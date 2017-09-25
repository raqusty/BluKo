package com.raqust.bluko.common;

public interface IBasePageOperations {
    /**
     * 初始化界面
     * 仅在某些不能使用注解方式获得控件实例的情况才需要实现该方法，
     * 否则可以不实现任何代码。
     */
    void initViews();

    /**
     * 设置监听器
     * 仅在某些不能使用注解方式绑定控件的监听的情况才需要实现该方法，
     * 否则可以不实现任何代码。
     */
    void setListener();

    /**
     * 刷新页面
     * 改方法应先在基类实现，如果子类有需要用到此功能再去重写该方法
     */
    void refreshPage();
}
