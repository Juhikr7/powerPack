package cc.altius.powerpack.dao;

import cc.altius.powerpack.model.User;

import java.util.List;

public interface UserDao {

    public List<User> getUserList();

    public int addUser(User user);

    public User getUserByUserId(int userId);

    public int editUser(User user);

    public User loadUserByUsername(String username);
}