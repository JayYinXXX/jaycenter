package center.jay.jaycenter.service.impl;

import center.jay.jaycenter.mapper.ArticleMapper;
import center.jay.jaycenter.mapper.CategoryMapper;
import center.jay.jaycenter.mapper.UserMapper;
import center.jay.jaycenter.pojo.Article;
import center.jay.jaycenter.pojo.Category;
import center.jay.jaycenter.pojo.User;
import center.jay.jaycenter.service.ArticleService;
import center.jay.jaycenter.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public Category get(Integer id) {
        Category category = categoryMapper.get(id);
//        setUser(category);
        return category;
    }

    @Override
    public List<Category> list() {
        List<Category> cs = categoryMapper.list();
//        for (Category c : cs) {
//            fillUser(c);
//        }
        return cs;
    }

    @Override
    public List<Category> listByUid(int uid) {
        List<Category> cs = categoryMapper.listByUid(uid);
//        for (Category c : cs) {
//            fillUser(c);
//        }
        return cs;
    }

    // 将uid包装成对应的类
    @Override
    public void fillUser(Category c) {
        int uid = c.getUid();
        User u = userMapper.get(uid);
        c.setUser(u);
    }
    // 注入当前category包含的article集合
    @Override
    public void fillArticles(Category c) {
        List<Article> as = articleMapper.listByCid(c.getId());
        c.setArticles(as);
    }
    @Override
    public void fillArticles(List<Category> cs) {
        for (Category c : cs) {
            fillArticles(c);
        }
    }
}
