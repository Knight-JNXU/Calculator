package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Knigh on 2016/9/4.
 */
public interface LoginService extends BaseService {
    public boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
