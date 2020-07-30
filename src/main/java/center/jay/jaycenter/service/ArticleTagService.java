package center.jay.jaycenter.service;

import center.jay.jaycenter.pojo.Article;
import center.jay.jaycenter.pojo.ArticleTag;
import center.jay.jaycenter.pojo.ArticleTagMap;

import java.util.List;

public interface ArticleTagService {
    // 标签管理
    void add(ArticleTag articleTag);
    void delete(int id);
    void update(ArticleTag articleTag);
    ArticleTag get(int id);
    List<ArticleTag> listByUid(int uid);

    void addMap(ArticleTagMap articleTag);
    void deleteMap(int aid, int tid);

    // 条件查询
    // 某文章已有标签
    List<ArticleTag> listByAid(int aid);
    // 某文章剩余可选标签
    List<ArticleTag> listRemainByAid(int aid);

}
