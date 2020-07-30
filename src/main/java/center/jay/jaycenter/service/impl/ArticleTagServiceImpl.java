package center.jay.jaycenter.service.impl;

import center.jay.jaycenter.mapper.ArticleMapper;
import center.jay.jaycenter.mapper.ArticleTagMapMapper;
import center.jay.jaycenter.mapper.ArticleTagMapper;
import center.jay.jaycenter.pojo.Article;
import center.jay.jaycenter.pojo.ArticleTag;
import center.jay.jaycenter.pojo.ArticleTagMap;
import center.jay.jaycenter.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ArticleTagServiceImpl implements ArticleTagService {
    @Autowired
    ArticleTagMapper articleTagMapper;
    @Autowired
    ArticleTagMapMapper articleTagMapMapper;
    @Autowired
    ArticleMapper articleMapper;

    // 标签管理
    @Override
    public void add(ArticleTag articleTag) {
        articleTagMapper.add(articleTag);
    }

    @Override
    public void delete(int id) {
        // 先检查外键约束，没有依赖项才能删除
        List<ArticleTagMap> atms = articleTagMapMapper.listByTid(id);
        if (atms.isEmpty()) {
            articleTagMapper.delete(id);
        }
    }

    @Override
    public void update(ArticleTag articleTag) {
        articleTagMapper.update(articleTag);
    }

    @Override
    public ArticleTag get(int id) {
        return articleTagMapper.get(id);
    }

    @Override
    public List<ArticleTag> listByUid(int uid) {
        return articleTagMapper.listByUid(uid);
    }

    @Override
    public void addMap(ArticleTagMap articleTagMap) {
        articleTagMapMapper.add(articleTagMap);
    }

    @Override
    public void deleteMap(int aid, int tid) {
        articleTagMapMapper.delete(aid, tid);
    }

    // 条件查询

    // 某文章已有标签
    @Override
    public List<ArticleTag> listByAid(int aid) {
        List<ArticleTag> result = new ArrayList<>();
        List<ArticleTagMap> atms = articleTagMapMapper.listByAid(aid);
        for (ArticleTagMap atm : atms) {
            result.add(get(atm.getTid()));
        }
        return result;
    }
    // 某文章剩余可选标签
    @Override
    public List<ArticleTag> listRemainByAid(int aid) {
        int uid = articleMapper.get(aid).getUid();
        // 属于某用户的所有标签
        List<ArticleTag> allTags = listByUid(uid);
        // 文章已有的标签
        List<ArticleTag> ownedTags = listByAid(aid);
        // 剩余可选标签
        while (!ownedTags.isEmpty()) {
            ArticleTag a = ownedTags.get(0);
            int i = 0;
            while (i <= allTags.size()-1) {
                ArticleTag b = allTags.get(i);
                if (a.getId().equals(b.getId())) {
                    ownedTags.remove(0);
                    allTags.remove(i);
                    break;
                }
                ++i;
            }
        }
        return allTags;
    }
}
