package cc.altius.powerpack.service;

import cc.altius.powerpack.model.User;

import java.util.List;

public interface UserService {

    public List<User> getUserList();

    public String addUser(User user);

    public User getUserByUserId(int userId);

    public int editUser(User user);
}
