package center.jay.jaycenter.mapper;

import center.jay.jaycenter.pojo.ArticleContent;

public interface ArticleContentMapper {
    void add(ArticleContent ac);

    void delete(Integer id);

    void update(ArticleContent ac);

    // ArticleContent get(Integer id);

    ArticleContent getByAid(Integer aid);
}
