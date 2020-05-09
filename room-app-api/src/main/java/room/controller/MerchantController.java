package room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import room.pojo.Merchant;
import room.service.MerchantService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/pms")
public class MerchantController {

    //注意：这里要这样引入，并且是接口Service，不是ServiceImpl，看完请删除该注释
    @Autowired
    private MerchantService merchantService;

    //一个接口测试成功以后，删除System打印相关，看完请删除该注释~~~~~~~
    @GetMapping("/register")
    public int register(@RequestParam(value = "account") int account,
                        @RequestParam(value = "password") String  password,
                        @RequestParam(value = "brand_name") String  brand_name){

        System.out.println(account+","+password+","+brand_name);

        if(merchantService.isAccountExist(account)) {
            System.out.println("账号已存在，注册失败!");
            return 0;//账号已存在，注册失败
        }else{
            Merchant merchant = new Merchant();
            merchant.setAccount(account);
            merchant.setPassword(password);
            merchant.setBrandName(brand_name);
            merchantService.createMerchant(merchant);
            System.out.println("账号注册成功!");
            return 1;//账号注册成功
        }
    }

    @GetMapping("/login")
    public int login(@RequestParam(value = "account") int account,
                     @RequestParam(value = "password") int password,
                     HttpSession session){

        System.out.println(account+","+password);

        if(merchantService.isAccountExist(account)) {
            Merchant merchant = merchantService.queryMerchantByAccount(account);
            String pwd = merchant.getPassword();
            if (pwd.equals(password)){
                System.out.println("登录成功！");
                int id = merchant.getMerchantId();
                session.setAttribute("id",id);
                session.setAttribute("account",account);
                session.setAttribute("id",id);
                session.setAttribute("account",account);

                Object account1 = session.getAttribute("account");
                System.out.println("accoutn:"+account1);

                return 1;//登录成功
            }else{
                return 0;//密码错误，登陆失败
            }
        }else{
            System.out.println("账号不存在，请先注册账号！");
            return 2;//账号不存在，请先注册账号
        }
    }
}
