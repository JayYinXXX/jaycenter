package center.jay.jaycenter.mapper;

import center.jay.jaycenter.pojo.ArticleTagMap;

import java.util.List;

public interface ArticleTagMapMapper {
    void add(ArticleTagMap articleTagMap);
    void delete(int aid, int tid);
    List<ArticleTagMap> listByAid(int aid);
    List<ArticleTagMap> listByTid(int tid);
}
