package room.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import room.pojo.Merchant;
import room.service.MerchantService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("/pms")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;
    private boolean status = false;//检测商家是否为登录状态

    //商家注册账号
    @GetMapping("/register")
    public int register(@RequestParam(value = "account") int account,
                        @RequestParam(value = "password") String  password,
                        @RequestParam(value = "brand_name") String  brand_name){
        //先判断用户是否尚为登录状态
        if (status){
            System.out.println("请先退出登录，再进行注册！");
            return 2;
        }
        else if(merchantService.isAccountExist(account)) {
            System.out.println("账号已存在，请直接返回首页登录!");
            return 0;//账号已存在，注册失败
        }else{
            //相关账号密码有效性验证，看前端如何验证（待补）
            Merchant merchant = new Merchant();
            merchant.setAccount(account);
            merchant.setBrandName(brand_name);
            merchant.setPassword(password);
            merchantService.createMerchant(merchant);
            System.out.println("账号注册成功!");
            return 1;//账号注册成功
        }
    }

    //商家登录
    @GetMapping("/login")
    public int login(@RequestParam(value = "account") int account,
                     @RequestParam(value = "password") String password,
                     HttpServletRequest request){

        if(merchantService.isAccountExist(account)) {
            if (merchantService.queryMerchantForLogin(account,password)){
                Merchant merchant = merchantService.queryMerchantByAccount(account);
                request.getSession().setAttribute("id",merchant.getMerchantId());
                System.out.println("登录成功！");
                status = true;//置登录状态量为真
                return 1;//登录成功
            }else{
                System.out.println("密码错误，登陆失败！");
                return 0;//密码错误，登陆失败
            }
        }else{
            System.out.println("账号不存在，请先注册账号！");
            return 2;//账号不存在，请先注册账号
        }
    }

    //商家修改账号密码
    @GetMapping("/updatePassword")
    public int update(HttpServletRequest request,
                      @RequestParam(value = "oldPassword") String oldPassword,
                      @RequestParam(value = "newPassword") String newPassword){
        //1.先判断用户的oldPassword是否正确
        int id = Integer.parseInt(request.getSession().getAttribute("id").toString());
        //String correctPwd = merchantService.queryMerchantById(id).getPassword();
        if (!merchantService.updateMerchantPassword(id,oldPassword,newPassword)){ //不正确
            System.out.println("用户输入的旧密码错误，修改不成功！");
            return 2;
        }
        //2.再判断两次输入的密码是否一致
        else if(StringUtils.equals(oldPassword,newPassword)){
            System.out.println("与原密码一致，修改不成功！");
            return 0;
        }else {
            System.out.println("修改密码成功！");
            return 1;
        }
    }

    //商家修改brand_name
    @GetMapping("/updateBrandName")
    public int updateBrandName(HttpServletRequest request,
                      @RequestParam(value = "brand_name") String brand_name){
        int id = Integer.parseInt(request.getSession().getAttribute("id").toString());
        Merchant merchant = merchantService.queryMerchantById(id);
        if(brand_name.equals(merchant.getBrandName())){
            System.out.println("与brand_name一致，修改不成功！");
            return 0;
        }else {
            merchant.setBrandName(brand_name);
            merchantService.updateMerchantById(merchant);
            System.out.println("修改brand_name成功！");
            return 1;
        }
    }

    //商家退出登录
    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        //清空session
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        status = false;//置登录状态量为假
        System.out.println("商家已退出登录！");
    }

    //商家注销账号
    @GetMapping("/logoff")
    public void logoff(HttpServletRequest request){
        //删除merchant表中该商家账号
        int id = Integer.parseInt(request.getSession().getAttribute("id").toString());
        merchantService.deleteMerchant(id);
        //删除order表中该商家所属订单（待补）

        //删除pension表中该商家所属民宿（待补）

        //清空session
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        status = false;//置登录状态量为假
        System.out.println("注销成功！");
    }



}
