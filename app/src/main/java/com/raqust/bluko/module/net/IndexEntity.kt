package com.raqust.bluko.module.net

/**
 * Created by linzehao
 * time: 2018/2/20.
 * info:
 */

data class IndexEntity(
        var page: Int = 0, //1
        var pageSize: Int = 0, //10
        var hasNext: Boolean = false, //false
        var totalPage: Int = 0, //1
        var list: List<Data> = listOf()
)

data class Data(
        var articleId: String = "", //文章id
        var title: String = "",//标题
        var content: String = "",//内容
        var author: BaseUserInfo = BaseUserInfo(),//作者
        var publishTime: Long = 0,//发布时间
        var firstTopic: TopicInfo = TopicInfo(),//参与的第一个话题
        var tags: List<TagInfo> = listOf(),//标签数组
        var showType: Int = 0,//1、图文    2、视频    3、长文
        var articleHeaderResp : ArticleHeader = ArticleHeader(), //文章头部

        var isWatchAuthor: Boolean = false, //是否已关注
        var isCommentable: Boolean = false,//是否可评论
        var isLikable: Boolean = false,//是否可点赞
        var isLiked: Boolean = false,//是否点赞过
        var commentCount: Long = 0,//评论数
        var likeCount: Long = 0,//点赞数
        var readCount: Long = 0,//阅读数
        var shareCount: Long = 0,//分享数
        var forwardedCount: Long = 0,//转发数

        var srcArticleId: String = "",//转发的文章id
        var originArticle: ForwardInfo = ForwardInfo(),//转发最源头的文章基本信息
        var commentUsers: List<BaseUserInfo> = listOf(),//评论用户数组
        var commentInfos: List<CommentInfo> = listOf(),//评论信息
        var likeUsers: List<BaseUserInfo> = listOf(),//点赞的用户信息
        var forwardeds: List<BaseUserInfo> = listOf(),//转发的用户信息

        var isRich: Boolean = false,//是否长文章
        var cover: String = "",//长文章/视频 封面
        var video: String = "",//视频
        var summary: String = "",//摘要
        var images: List<ImageInfo> = listOf(),//包含宽高的图片url

        var sourceUrl: String = "",//来源url
        var sourcePlatform: String = "",//来源平台
        var weight: Int = 0,//权重

        //主页需求加入两个字段
        var isTop : Boolean = false,//是否置顶
        var isEssential : Boolean = false,//是否精华

        var noteSource : Boolean = false,//是否标注来源
        var originalAuthorName : String = ""//后台设置的希望展示的原作者名称
)

open class BaseUserInfo(
        var userId: Long = 0, //星飞id
        var avatar: String = "", //头像
        var nickname: String = "", //昵称
        var userGroupType: Int = 1//1、普通组、2、马甲组、3、认证组
)

data class ArticleHeader(
        var icon: String = "",
        var name: String = "",
        var type: Int = 0
)

data class CommentInfo(
        var id: Int = 0,
        var userId: Long = 0,
        var articleId: String = "",
        var author: BaseUserInfo = BaseUserInfo(),
        var content: String = "",
        var gmtCreate: Long = 0,
        var gmtModified: Long = 0,
        var isRich: Boolean = false,
        var likeCount: Int = 0,
        var replyCount: Int = 0,
        var status: Int = 0,
        var statusMsg: String = "",
        var summary: String = "",
        var title: String = ""
)

data class TopicInfo(
        var topicId: String = "",
        var topic: String = "",
        var imageUrl: String = "",
        var content: String = "",
        var readCount: Long = 0,
        var attendCount: Long = 0
)

data class ImageInfo(
        var imageUrl: String = "",
        var width: Int = 0,
        var height: Int = 0
)

data class TagInfo(
        var mark: String = "",
        var name: String = "",
        var tagId: Int = 0
)

data class ForwardInfo(
        var articleId: String = "",
        var content: String = "",
        var cover: String = "",
        var author: BaseUserInfo = BaseUserInfo(),
        var images: List<ImageInfo> = listOf(),
        var isRich: Boolean = false,
        var showType: Int = 0,
        var summary: String = "",
        var tags: List<TagInfo> = listOf(),
        var title: String = "",
        var video: String = ""
)