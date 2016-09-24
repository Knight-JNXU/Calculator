package dao.impl;

import dao.LoginDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Knigh on 2016/9/4.
 */
@Repository
public class LoginDaoImpl extends BaseDaoImpl implements LoginDao {
    @Value("#{userInfo.username}")
    private String username;
    @Value(value = "#{userInfo.password}")
    private String passwd;

    public boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String un = request.getParameter("username");
        String pw = request.getParameter("password");
        String userFile = readFileByLines(userFilePath);
        String strs[] = userFile.split("\\\n");
        if(strs.length > 1){
            String nameStr[] = strs[0].split(",");
            String passwdStr[] = strs[1].split(",");
            for (int i=0; i<nameStr.length; i++){
                if(un.equals(nameStr[i]) && pw.equals(passwdStr[i])){
                    request.getSession().setAttribute("user", un);
                    return true;
                }
            }
        }
        if(un.equals(username) && pw.equals(passwd)){
            request.getSession().setAttribute("user", un);
            request.getSession().setAttribute("manager", "true");
            return true;
        }
        return false;
    }
}
