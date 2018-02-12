package com.raqust.bluko.module.net

/**
 * Created by linzehao
 * time: 2018/2/9.
 * info:
 */
data class LoginEntity(
        var token: String = "", //6edcbb7ce3731922
        var userInfo: Boolean = false,
        var userInfoData: UserInfoData = UserInfoData()
)


data class UserInfoData(
        val appUser: AppUser = AppUser(), //移动端用户信息
        val authenticationDto: AuthenticationDto = AuthenticationDto(), //用户认证分类信息
        val id: Int = 0, //0 用户id
        val sex: Int = 0, //0 用户性别：1、男；2、女
        val username: String = "", //string 手机号
        val xfId: Long = 0 //0 星飞ID
)

data class AppUser(
        val autograph: String = "", //string 签名
        val avatar: String = "", //string 用户头像地址
        val company: String = "", //string 公司名称
        val id: Long = 0, //0
        val level: String = "", //string 等级
        val nickname: String = "", //string 用户昵称
        val profession: Int = 0, //0  用户分类：1、从业人员；2、玩家
        val professionInfo: Int = 0, //0 用户为从业人员时：1、策划；2、技术；3、设计；4、项目管理；5、游戏运营；6、市场营销；7、商务渠道；8、职能
        val profile: String = "", //string 个人简介
        val userId: Long = 0, //0 用户ID
        val weight: Int = 0 //0  用户权重
)

data class AuthenticationDto(
        val gmtCreate: String = "", //2018-02-01T09:04:33.524Z
        val gmtModified: String = "", //2018-02-01T09:04:33.524Z
        val id: Long = 0, //0
        val mark: String = "", //string 认证备注
        val name: String = "" //string 认证分类名称
)