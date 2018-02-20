package com.raqust.bluko.module.user

import android.text.TextUtils
import android.util.Log
import com.raqust.bluko.common.utils.PreferStorage
import com.raqust.bluko.module.net.LoginEntity
import com.raqust.bluko.module.net.UserInfoData

/**
 * Created by linzehao
 * time: 2018/2/20.
 * info:
 */
object UserInfo {
    /**
     * 后台有的字段
     */
    var token by PreferStorage("")// t o k e n
    var autograph by PreferStorage("")//签名
    var company by PreferStorage("")//公司名称
    var level by PreferStorage("") //string 等级
    var avatar by PreferStorage("")//用户头像
    var username by PreferStorage("")//手机号,用户名，账号
    var nickname by PreferStorage("")//用户昵称
    var sex by PreferStorage(0)//用户性别：1、男；2、女 ,
    var profession by PreferStorage(0)//用户职业：1、从业人员；2、玩家 ,
    var professionInfo by PreferStorage(0)//用户为从业人员时：1、策划；2、技术；3、设计；4、项目管理；5、游戏运营；6、市场营销；7、商务渠道；8、职能
    var profile by PreferStorage("") //string 个人简介


    var gmtCreate by PreferStorage("")//创建时间 ,
    var gmtModified by PreferStorage("")//更新时间 ,
    var mark by PreferStorage("") //string 认证备注
    var name by PreferStorage("")//string 认证分类名称

    /**
     * 客户端拓展的字段
     */
    var uid by PreferStorage(0L)//后台没有，保留这个字段
    var isLogin by PreferStorage(false)//是否登录   目前是虽然可以通过判断token来确定是否登录，但为了方便后期不同逻辑，所以加多这个字段
    var isComplie by PreferStorage(false)//数据是否完成

    /**
     * 设置用户信息
     */
    fun setUser(data: LoginEntity?) {
        //这里一定要这样处理，因为存数据不能为空
        token = data?.token ?: ""
        isComplie = data?.userInfo ?: false
        setUser(data?.userInfoData)
        isLogin = true
    }

    fun setUser(data: UserInfoData?) {
        if (data!=null){
            sex = data.sex
            username = data.username
            uid = data.id

            val appUser = data.appUser
            autograph = appUser.autograph ?: ""
            avatar = appUser.avatar ?: ""
            company = appUser.company ?: ""
            level = appUser.level ?: ""
            nickname = appUser.nickname ?: ""
            profession = appUser.profession ?: 0
            professionInfo = appUser.professionInfo ?: 0
            profile = appUser.profile ?: ""

            val authenticationDto = data.authenticationDto
            gmtCreate = authenticationDto.gmtCreate ?: ""
            gmtModified = authenticationDto.gmtModified ?: ""
            mark = authenticationDto.mark ?: ""
            name = authenticationDto.name ?: ""
            //这里就完成登录了
            isLogin = true
        }

    }

    /**
     * 设置token 和 是否完成人物信息
     */
    fun setToken(token: String, isComplie: Boolean = true) {
        Log.i("TOKE", token)
        this.token = token
        this.isComplie = isComplie
    }

    /**
     * 清除用户数据
     */
    fun clearUserInfo() {
        token = ""
        autograph = ""
        company = ""
        level = ""
        avatar = ""
        username = ""
        nickname = ""
        sex = 0
        profession = 0
        professionInfo = 0
        profile = ""
        gmtCreate = ""
        gmtModified = ""
        mark = ""
        name = ""
        uid = 0L
        isComplie = false

        //这里就退出登录了
        isLogin = false
    }

    /**
     * 判断是否登录
     */
    fun getIsLogin(): Boolean {
        return !TextUtils.isEmpty(token) && isLogin
    }

}