package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Knigh on 2016/9/3.
 */
@Controller
@RequestMapping(value = "/loginController")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(loginService.checkLogin(request, response))
        {
            if(request.getSession().getAttribute("manager")!=null &&
                    request.getSession().getAttribute("manager").equals("true")){
                return "redirect:/userController/goManager";
            }else{
                System.out.println("manager");
                return "redirect:/characterController/getAllCharacter";
            }
        }else{
            return "login";
        }
    }

    @RequestMapping(value = "/go")
    public String go(){
        return "login";
    }

}
