package center.jay.jaycenter.service;

import center.jay.jaycenter.pojo.Article;
import center.jay.jaycenter.pojo.Category;
import center.jay.jaycenter.pojo.User;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    void delete(Integer id);

    void update(Category category);

    Category get(Integer id);

    List<Category> list();

    List<Category> listByUid(int uid);

    // 将uid包装成对应的类
    void fillUser(Category c);
    // 注入当前分类包含的article集合
    void fillArticles(Category c);
    void fillArticles(List<Category> cs);
}
