package com.haimian.bl.controller;

import com.haimian.bl.bean.UserBean;
import com.haimian.bl.service.UserMessageService;
import com.haimian.bl.web3j.GenerateETHaddr;
import com.haimian.bl.web3j.HashUtil;
import com.haimian.bl.web3j.Web3jOpera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DealController {

    private final  static String CODE_NUM = "1234";


    @RequestMapping(value = "/loginVerifty")
    public Map<String,String> loginVerifty(HttpServletRequest request) throws Exception {

        String userName = request.getParameter("userName");
        String passworld = request.getParameter("passworld");
        String verificationCode = request.getParameter("verificationCode");
        System.out.println("已经收到值了" + userName + passworld + verificationCode);
        HashMap<String, String> hashMap = new HashMap<>();

        UserBean userBean;
        UserMessageService userMessageService = new UserMessageService();
        userBean = userMessageService.select(userName);

        if(!userName.equals(userBean.getId_card())) {
            hashMap.put("boolean","userNameError");
            System.out.println("返回userNameError");
            return hashMap;
        } else if(!HashUtil.md5(passworld.getBytes()).equals(userBean.getPassword())) {
            hashMap.put("boolean", "passworldError");
            System.out.println("返回passworldError");
            return hashMap;
        } else if(!verificationCode.equals(CODE_NUM)) {
            hashMap.put("boolean", "verificationCodeError");
            System.out.println("返回verificationCodeError");
            return hashMap;
        } else if (userBean.getStates()==9) {
            hashMap.put("boolean","god");
            System.out.println("是管理员账户");
            return hashMap;
        } else {
//            OperToken operToken = new OperToken();
//            String greet = operToken.webGetGreet();
            hashMap.put("boolean", "true");
//            hashMap.put("greet",greet);
            System.out.println("返回正确答案");
            return hashMap;
        }
    }


    @RequestMapping(value = "/register")
    public Map<String,Object> Register(HttpServletRequest request) throws Exception {

        String userName = request.getParameter("userName");
        String passworld = request.getParameter("passworld");
        String verificationCode = request.getParameter("verificationCode");
        System.out.println("注册已经收到值了" + userName + passworld + verificationCode);
        HashMap<String, Object> hashMap = new HashMap<>();
        UserMessageService userMessageService = new UserMessageService();
        UserBean userBean2 = userMessageService.select(userName);
        //验证账户是否重复注册
        if(userBean2.getScore() != 0) {
            hashMap.put("boolean","chongfu");
            System.out.println("新用户重复");
            return hashMap;
        }
        UserBean userBean = GenerateETHaddr.createAccount(passworld);
        userBean.setId_card(userName);
        userBean.setPassword(HashUtil.md5(passworld.getBytes()));
        boolean isSuccess = userMessageService.insert(userBean);

        Web3jOpera web3jOpera = new Web3jOpera();
        System.out.println("Controller里的" + userBean.getEth_address()  + passworld);
        web3jOpera.createUser(userBean, passworld);
        System.out.println("插入新用户" + isSuccess);
        if(isSuccess) {
            hashMap.put("boolean", true);
            return hashMap;
        } else {
            hashMap.put("boolean",false);
            return hashMap;

        }
    }

    @RequestMapping(value = "/messageInfo")
    public Map<String,Object> messageInfo(
            @RequestParam String id_card
    ) throws SQLException {
        System.out.println("登录成功后进入messageInfo id_card:" + id_card);
        UserMessageService userMessageService = new UserMessageService();
        UserBean userBean = userMessageService.select(id_card);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("eth_address",userBean.getEth_address());
        hashMap.put("score",userBean.getScore());
        return hashMap;
    }

    @RequestMapping(value = "/godSetScore")
    public Map<String,Object> godSetScore(HttpServletRequest request) throws Exception {
        String id_card = request.getParameter("id_card");
        String score = request.getParameter("score");
        System.out.println("ID名:" + id_card);
        System.out.println("收集到分数了：" + score);
        UserMessageService userMessageService = new UserMessageService();
        userMessageService.update(Integer.parseInt(score), id_card);
        UserBean userBean = userMessageService.select(id_card);
        Web3jOpera web3jOpera = new Web3jOpera();
        BigInteger bigScore = new BigInteger(score);
        System.out.println(bigScore);
        boolean isUp = web3jOpera.ownerSetScore(userBean,bigScore);
        System.out.println("是否修改成功:" + isUp);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("boolean", "true");
        return hashMap;
    }

    @RequestMapping(value = "/godGetScore")
    public Map<String,Object> godGetScore(HttpServletRequest request) throws Exception {
        String id_card = request.getParameter("id_card");
        UserMessageService userMessageService = new UserMessageService();
        UserBean userBean = userMessageService.select(id_card);
        Web3jOpera web3jOpera = new Web3jOpera();
        Integer score = web3jOpera.ownGetScore(userBean.getEth_address());
        System.out.println("分数为：" + score);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("score", score);
        return hashMap;
    }
}
