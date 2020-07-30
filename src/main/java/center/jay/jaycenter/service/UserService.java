package center.jay.jaycenter.service;

import center.jay.jaycenter.pojo.User;

import java.util.List;

public interface UserService {
    void add(User user);

    User get(Integer id);

    User getByNameAndPassword(String name, String password);

    List<User> list();

    // 该账号名是否存在
    boolean isExist(String name);
}
