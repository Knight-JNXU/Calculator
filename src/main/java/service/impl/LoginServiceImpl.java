package service.impl;

import dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.LoginService;
import service.impl.BaseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Knigh on 2016/9/4.
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    public boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
        return loginDao.checkLogin(request, response);
    }
}
