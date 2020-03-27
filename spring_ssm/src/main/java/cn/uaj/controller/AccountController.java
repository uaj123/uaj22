package cn.uaj.controller;

import cn.uaj.entity.Account;
import cn.uaj.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 账户的控制器
 * @Author: wushaojie
 * @Date: 2020/2/9 17:55
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @RequestMapping("/findAllAccount")
    public String findAllAccount(Model model){
        System.out.println("表现层查询成功...");
        List<Account> accountList = accountService.findAll();
        model.addAttribute("accountList",accountList);
        return "list";
    }

    @RequestMapping("/saveAccount")
    public void saveAccount(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
        accountService.saveAccount(account);
        /*重定向*/
        response.sendRedirect(request.getContextPath() + "/account/findAllAccount");
    }
}
