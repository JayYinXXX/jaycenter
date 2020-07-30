package center.jay.jaycenter.service;

import center.jay.jaycenter.pojo.ArticleContent;

public interface ArticleContentService {
    void add(ArticleContent ac);

    void delete(int id);
    void update(ArticleContent ac);
    ArticleContent getByAid(int aid);
}
