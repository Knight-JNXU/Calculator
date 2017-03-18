package dao.impl;

import dao.LoginDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Knigh on 2016/9/4.
 */
@Repository
@Configuration
@PropertySource(value = "classpath:userInfo.properties")
public class LoginDaoImpl extends BaseDaoImpl implements LoginDao {

    @Value("#{'${usernames}'.split(',')}")
    private List<String> usernames;
    @Value("#{'${passwords}'.split(',')}")
    private List<String> passwords;

    public boolean checkLogin(HttpServletRequest request) throws Exception{
        String un = request.getParameter("username");
        String pw = request.getParameter("password");
//        String userFile = readFileByLines((urlHeader+userFilePath));
//        String strs[] = userFile.split("\\\n");
//        if(strs.length > 1){
//            String nameStr[] = strs[0].split(",");
//            String passwdStr[] = strs[1].split(",");
//            for (int i=0; i<nameStr.length; i++){
//                if(un.equals(nameStr[i]) && pw.equals(passwdStr[i])){
//                    request.getSession().setAttribute("user", un);
//                    return true;
//                }
//            }
//        }
        for(int i=0; i<usernames.size(); i++){
            String username = usernames.get(i);
            String passwd = passwords.get(i);
            if(un.equals(username) && pw.equals(passwd)){
                request.getSession().setAttribute("user", un);
                request.getSession().setAttribute("manager", "true");
                return true;
            }
        }
        return false;
    }
}
