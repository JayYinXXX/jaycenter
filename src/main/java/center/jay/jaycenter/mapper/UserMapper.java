package center.jay.jaycenter.mapper;

import center.jay.jaycenter.pojo.User;

import java.util.List;

public interface UserMapper {
    void add(User user);
    // void delete(Integer id);
    // void update(Integer id);
    User get(Integer id);
    User getByNameAndPassword(String name, String password);
    List<User> list();
    List<User> listByName(String name);
}
