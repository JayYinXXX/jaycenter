package center.jay.jaycenter.mapper;

import center.jay.jaycenter.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    void add(Category category);

    void delete(Integer id);

    void update(Category category);

    Category get(Integer id);

    List<Category> list();
    // 条件查询
    List<Category> listByUid(Integer uid);
}
