package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Knigh on 2016/9/15.
 */
@Controller
@RequestMapping(value = "/userController")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/goManager")
    public String goManager(){
        return "manager";
    }

    @RequestMapping(value = "/goAddUser")
    public String goAddUser(){
        return "adduser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, Model model) throws Exception{
        String user = request.getParameter("user");
        String passwd = request.getParameter("passwd");
        String resultStr = "添加 user 失败！";
        if(userService.addUser(user, passwd)){
            resultStr = "添加 user 成功！";
        }
        model.addAttribute("resultStr", resultStr);
        model.addAttribute("targetUrl", (request.getContextPath()+"/userController/goAddUser"));
        return "result";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("user", null);
        request.getSession().setAttribute("manager", null);
        System.out.println("logout");
        return "login";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String goAbout(){
        return "about";
    }
}
