package center.jay.jaycenter.service.impl;

import center.jay.jaycenter.mapper.ArticleMapper;
import center.jay.jaycenter.mapper.ArticleTagMapMapper;
import center.jay.jaycenter.pojo.Article;
import center.jay.jaycenter.pojo.ArticleTag;
import center.jay.jaycenter.pojo.ArticleTagMap;
import center.jay.jaycenter.service.ArticleService;
import center.jay.jaycenter.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleTagMapMapper articleTagMapMapper;
    @Autowired
    ArticleTagService articleTagService;

    @Override
    public void add(Article article) {
        articleMapper.add(article);
    }

    @Override
    public void delete(int id) {
        articleMapper.delete(id);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    @Override
    public Article get(int id) {
        return articleMapper.get(id);
    }

    @Override
    public List<Article> list() {
        return articleMapper.list();
    }

    @Override
    public List<Article> listByUid(int uid) {
        return articleMapper.listByUid(uid);
    }

    @Override
    public List<Article> listByCid(int cid) {
        return articleMapper.listByCid(cid);
    }

    @Override
    public List<Article> listByCreateDate(int uid, int n) {
        return articleMapper.listByCreateDate(uid, n);
    }

    @Override
    public List<Article> listByTop(int uid) {
        return articleMapper.listByTop(uid);
    }

    @Override
    public List<Article> latestArticles(int uid, int n) {
        List<Article> list = listByTop(uid);

        // 去除重复的置顶文章
        List<Article> list2 = listByCreateDate(uid, n);
        removeTop(list2);

        list.addAll(list2);
        return list;
    }

    // 某标签包含的文章
    @Override
    public List<Article> listByTid(int tid) {
        List<Article> result = new ArrayList<>();
        List<ArticleTagMap> atms = articleTagMapMapper.listByTid(tid);
        for (ArticleTagMap atm : atms) {
            result.add(articleMapper.get(atm.getAid()));
        }
        return result;
    }

    @Override
    public void fillTags(Article a) {
        List<ArticleTag> ats = articleTagService.listByAid(a.getId());
        a.setTags(ats);
    }

    @Override
    public void fillTags(List<Article> as) {
        for (Article a : as) {
            fillTags(a);
        }
    }

    public void removeTop(List<Article> as) {
        int i = 0;
        while (i <= as.size()-1) {
            if (as.get(i).isTop()) {
                as.remove(i);
                continue;
            }
            ++i;
        }
    }

    public void removePrivate(List<Article> as) {
        int i = 0;
        while (i <= as.size()-1) {
            if (as.get(i).getType().equals(Article.PRIVATE)) {
                as.remove(i);
                continue;
            }
            ++i;
        }
    }
}
