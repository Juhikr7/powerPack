package cc.altius.powerpack.service.impl;

import cc.altius.powerpack.dao.UserDao;
import cc.altius.powerpack.model.EmailDetails;
import cc.altius.powerpack.model.User;
import cc.altius.powerpack.service.EmailService;
import cc.altius.powerpack.service.UserService;
import cc.altius.utils.PassPhrase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailService emailService;

    @Override
    public List<User> getUserList() {
        return this.userDao.getUserList();
    }

    @Override
    public String addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = PassPhrase.getPassword(8);
        user.setPassword(encoder.encode(password));
        int userid = userDao.addUser(user);
        EmailDetails emailDetails= new EmailDetails();
        emailDetails.setRecipient(user.getEmail());
        emailDetails.setSubject("User name and password to log in");
        emailDetails.setMsgBody("Dear "+user.getName()+",\n your User name is "+ user.getUsername()+" password is- "+password+" .\n Thank you !");
        if (userid > 0){
            emailService.sendSimpleMail(emailDetails);
            return "User Created with User name : " + user.getUsername()+" and login details sent on email !";
        }else {
            return "Error While Saving a User !!";
        }

    }

    @Override
    public User getUserByUserId(int userId) {
        return this.userDao.getUserByUserId(userId);
    }

    @Override
    public int editUser(User user) {
        return this.userDao.editUser(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return user;
        }
    }

}
