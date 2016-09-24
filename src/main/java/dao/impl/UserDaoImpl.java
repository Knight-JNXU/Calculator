package dao.impl;

import dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Knigh on 2016/9/15.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    public boolean addUser(String user, String passwd) throws Exception {
        return addUserInFile(user, passwd, (urlHeader+userFilePath));
    }
}
