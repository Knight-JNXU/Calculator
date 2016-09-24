package service.impl;

import dao.UserDao;
import dao.impl.BaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

/**
 * Created by Knigh on 2016/9/15.
 */
@Service
public class UserServiceImpl extends BaseDaoImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public boolean addUser(String user, String passwd) throws Exception {
        return userDao.addUser(user, passwd);
    }
}
