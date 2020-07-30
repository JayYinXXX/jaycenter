package center.jay.jaycenter.mapper;

import center.jay.jaycenter.pojo.ArticleTag;

import java.util.List;

public interface ArticleTagMapper {

    void add(ArticleTag articleTag);
    void delete(int id);
    void update(ArticleTag articleTag);
    ArticleTag get(int id);

    // 条件查询
    List<ArticleTag> listByUid(int uid);
}
