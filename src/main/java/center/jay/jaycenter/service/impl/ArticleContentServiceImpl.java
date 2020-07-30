package center.jay.jaycenter.service.impl;

import center.jay.jaycenter.mapper.ArticleContentMapper;
import center.jay.jaycenter.pojo.ArticleContent;
import center.jay.jaycenter.service.ArticleContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleContentServiceImpl implements ArticleContentService {
    @Autowired
    ArticleContentMapper articleContentMapper;
    @Override
    public void add(ArticleContent ac) {
        articleContentMapper.add(ac);
    }

    @Override
    public void delete(int id) {
        articleContentMapper.delete(id);
    }

    @Override
    public void update(ArticleContent ac) {
        articleContentMapper.update(ac);
    }

    @Override
    public ArticleContent getByAid(int aid) {
        return articleContentMapper.getByAid(aid);
    }
}
