package center.jay.jaycenter.service;

import center.jay.jaycenter.pojo.Article;

import java.util.List;

public interface ArticleService {
    void add(Article article);

    void delete(int id);

    void update(Article article);

    Article get(int id);

    List<Article> list();

    // 条件查询
    List<Article> listByUid(int uid);
    List<Article> listByCid(int cid);
    // 某标签包含的文章（不同用户直接的tag是不共享的）
    List<Article> listByTid(int tid);
    // 按创建日期查询，指定User的最新的n个记录
    List<Article> listByCreateDate(int uid, int n);
    // 查询置顶记录，按创建日期排序
    List<Article> listByTop(int uid);

    // 置顶+最新文章模块，置顶在前，然后按创建日期排序（优先保证显示所有置顶，最大显示n条记录）
    List<Article> latestArticles(int uid, int n);

    // 填充标签
    void fillTags(Article a);
    void fillTags(List<Article> as);

}
