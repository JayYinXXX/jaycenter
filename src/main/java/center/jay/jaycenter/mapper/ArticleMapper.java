package center.jay.jaycenter.mapper;

import center.jay.jaycenter.pojo.Article;

import java.util.List;

public interface ArticleMapper {
    void add(Article article);

    void delete(Integer id);

    // 逻辑删除，实际为update，更新数据库中的isDeleted字段
    void deleteLogic(Integer id);

    void update(Article article);

    Article get(Integer id);

    List<Article> list();

    // 条件查询
    List<Article> listByUid(Integer uid);
    // 列出指定分类下的所有文章（按rank升序排列）
    List<Article> listByCid(Integer cid);
    // 按创建日期查询
    List<Article> listByCreateDate(int uid, int n);
    // 列出置顶记录，按创建日期排序
    List<Article> listByTop(int uid);
}
