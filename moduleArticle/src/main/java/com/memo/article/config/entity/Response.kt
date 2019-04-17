package com.memo.article.config.entity

/**
 * title:请求实体类
 * describe:
 *
 * @author zhou
 * @date 2019-04-10 20:45
 */

/**
 * 主页文章
 */
data class Article(
    var curPage: Int = 0, // 1
    var datas: ArrayList<ArticleData> = arrayListOf(),
    var offset: Int = 0, // 0
    var over: Boolean = false, // false
    var pageCount: Int = 0, // 305
    var size: Int = 0, // 20
    var total: Int = 0 // 6085
)


data class ArticleData(
    var apkLink: String = "",
    var author: String = "", // MeandNi
    var chapterId: Int = 0, // 402
    var chapterName: String = "", // 跨平台应用
    var collect: Boolean = false, // false
    var courseId: Int = 0, // 13
    var desc: String = "", // 感谢那些热爱开源、一起奋斗的朋友们。值得学习的话，Star哦^_^
    var envelopePic: String = "", // http://wanandroid.com/blogimgs/6440711d-7ce1-4366-a60d-e14c339f8e06.png
    var fresh: Boolean = false, // false
    var id: Int = 0, // 7952
    var link: String = "", // http://www.wanandroid.com/blog/show/2501
    var niceDate: String = "", // 2019-02-17
    var origin: String = "",
    var originId: Int = -1,
    var projectLink: String = "", // https://github.com/MeandNi/Flutter_ZhiHu
    var publishTime: Long = 0, // 1550410055000
    var superChapterId: Int = 0, // 294
    var superChapterName: String = "", // 开源项目主Tab
    var tags: ArrayList<Tag> = arrayListOf(),
    var title: String = "", // Flutter知乎App实践
    var type: Int = 0, // 0
    var userId: Int = 0, // -1
    var visible: Int = 0, // 1
    var zan: Int = 0, // 0
    var isTop: Boolean = false
)

data class Tag(
    var name: String = "", // 项目
    var url: String = "" // /project/list/1?cid=402
)


/**
 * 首页轮播图
 */
data class MainBanner(
    var desc: String = "",
    var id: Int = 0, // 5
    var imagePath: String = "", // http://www.wanandroid.com/blogimgs/acc23063-1884-4925-bdf8-0b0364a7243e.png
    var isVisible: Int = 0, // 1
    var order: Int = 0, // 3
    var title: String = "", // 微信文章合集
    var type: Int = 0, // 1
    var url: String = "" // http://www.wanandroid.com/blog/show/6
)

/**
 * 首页数据合并
 */
data class MainData(
    var articles: Article? = null,
    var banners: List<MainBanner>? = null
)

/**
 * 搜索热词
 */
data class HotKeyword(
    var name: String = "" // 逆向 加固
)

data class Project(
    var courseId: Int = 0, // 13
    var id: Int = 0, // 294
    var name: String = "", // 完整项目
    var order: Int = 0, // 145000
    var parentChapterId: Int = 0, // 293
    var userControlSetTop: Boolean = false, // false
    var visible: Int = 0 // 0
)

data class Tree(
    var children: List<Project> = listOf(),
    var courseId: Int = 0, // 13
    var id: Int = 0, // 439
    var name: String = "", // 问答
    var order: Int = 0, // 200
    var parentChapterId: Int = 0, // 0
    var userControlSetTop: Boolean = false, // false
    var visible: Int = 0 // 1
)

