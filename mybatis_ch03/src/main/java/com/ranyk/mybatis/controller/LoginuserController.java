package com.ranyk.mybatis.controller;

import com.ranyk.mybatis.entity.Loginuser;
import com.ranyk.mybatis.service.LoginuserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 登录用户测试(Loginuser)表控制层
 *
 * @author makejava
 * @since 2020-06-28 18:19:56
 */
@RestController
@RequestMapping("/login_user/")
public class LoginuserController {
    /**
     * 服务对象
     */
    @Resource
    private LoginuserService loginService;


    @GetMapping("get_user/{id}")
    public Loginuser getUser(@PathVariable("id") Integer id){
        System.out.println(loginService.selectById(id));
        System.out.println(loginService.selectById(id));
        return loginService.selectById(id);
    }

    @GetMapping("get_user")
    public List<Loginuser> getUser(Loginuser loginuser){
        String checkSql0 = "/(\\%27)|(\\’)|(\\-\\-)|(\\%23)|(#)/ix";
        String checkSql1 = "/((\\%3D)|(=))[^\\n]*((\\%27)|(\\’)|(\\-\\-)|(\\%3B)|(:))/i";
        String checkSql2 = "/\\w*((\\%27)|(\\’))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix";
        String checkSql3 = "/((\\%27)|(\\’))union/ix(\\%27)|(\\’)";
        String checkSql4 = "/exec(\\s|\\+)+(s|x)p\\w+/ix";

        String SQL = "select * from loginuser where username = " + loginuser.getUsername() + " and password = " + loginuser.getPassword();

        System.out.println(Pattern.matches(checkSql0, SQL));
        System.out.println(Pattern.matches(checkSql1, SQL));
        System.out.println(Pattern.matches(checkSql2, SQL));
        System.out.println(Pattern.matches(checkSql3, SQL));
        System.out.println(Pattern.matches(checkSql4, SQL));

        return loginService.selectByLoginuser(loginuser);
    }


}