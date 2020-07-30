package center.jay.jaycenter.service.impl;

import center.jay.jaycenter.mapper.UserMapper;
import center.jay.jaycenter.pojo.User;
import center.jay.jaycenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public User get(Integer id) {
        return userMapper.get(id);
    }

    @Override
    public User getByNameAndPassword(String name, String password) {
        User u = userMapper.getByNameAndPassword(name, password);
        return u;
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public boolean isExist(String name) {
        List<User> us = userMapper.listByName(name);
        if (us.isEmpty()) {
            return false;
        }
        return true;
    }
}
