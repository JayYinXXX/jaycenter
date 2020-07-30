package center.jay.jaycenter.pojo;

import java.util.Date;
import java.util.List;

public class Article {
    // 数据库字段
    private Integer id;
    private String name;  // 文章标题
    private Integer cid;  // 所属分类id
    private Integer uid;  // 所属用户id
    private Date createDate;  // 创建日期
    private Integer updateUid;  // 更新用户id
    private Date updateDate;  // 更新日期
    private Integer deleteUid;  // 删除用户id
    private Date deleteDate;  // 删除日期
    private boolean isDeleted;  // 逻辑删除标记
    private Integer version;  // 版本号
    private boolean isTop;  // 是否置顶
    private String type;  // 文章类型（公开、私密、转载）
    private Integer readCount;  // 阅读数
    private Integer likeCount;  // 点赞数
    private Integer rank;  // 排序
    public static String PUBLIC = "public";
    public static String PRIVATE = "private";
    // 包装成类
    private Category category;
    private User user;
    private User updateUser;
    private User deleteUser;

    // 非数据库字段
    // 增加一个content对象
    private ArticleContent articleContent;
    // 增加一个tag的List
    private List<ArticleTag> tags;


    // Setter/Getter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDeleteUid() {
        return deleteUid;
    }

    public void setDeleteUid(Integer deleteUid) {
        this.deleteUid = deleteUid;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public User getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(User deleteUser) {
        this.deleteUser = deleteUser;
    }

    public ArticleContent getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(ArticleContent articleContent) {
        this.articleContent = articleContent;
    }

    public List<ArticleTag> getTags() {
        return tags;
    }

    public void setTags(List<ArticleTag> tags) {
        this.tags = tags;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
