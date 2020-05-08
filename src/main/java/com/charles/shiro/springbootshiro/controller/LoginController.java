package com.charles.shiro.springbootshiro.controller;

import com.charles.shiro.springbootshiro.entity.User;
import com.charles.shiro.springbootshiro.shiro.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangshengli
 * @data 2020/05/07
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login(User user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return "没有权限";
        }
        return "login success";
    }

    @RequestMapping("/clear")
    public String clear() {
        RealmSecurityManager rsm = (RealmSecurityManager)SecurityUtils.getSecurityManager();
        CustomRealm realm = (CustomRealm)rsm.getRealms().iterator().next();
        realm.clearAllCachedAuthenticationInfo();
        realm.clearAllCachedAuthorizationInfo();
        System.out.println("清除成功");
        return "清除成功";
    }

    @RequestMapping("/index")
    public String index() {
        return "添加成功!";
    }

    @RequestMapping("/loginFail")
    public String  loginFail() {
        return "请重新登陆!";
    }

    @RequestMapping("/unpermission")
    public String  unpermission() {
        return "权限不足!";
    }

}