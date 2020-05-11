package room.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import room.pojo.Merchant;
import room.pojo.bo.MerchantBO;
import room.service.MerchantService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    //商家注册账号
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  register(@RequestBody MerchantBO merchantBO){

        if(merchantService.isAccountExist(merchantBO.getAccount())) {
            JSONObject result = new JSONObject();
            result.put("status","failure");
            result.put("detail","账号已存在，请直接返回首页登录!");
            return result.toJSONString();
        }else{
            //相关账号密码有效性验证，看前端如何验证（待补）
            Merchant merchant = new Merchant();
            merchant.setAccount(merchantBO.getAccount());
            merchant.setBrandName(merchantBO.getBrandName());
            merchant.setPassword(merchantBO.getPwd());
            merchantService.createMerchant(merchant);
            JSONObject result = new JSONObject();
            result.put("status", "success");
            result.put("detail","账号注册成功！");
            return result.toJSONString();
        }
    }

    //商家登录
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestBody MerchantBO merchantBO,
                        HttpServletRequest request){
        if(merchantService.isAccountExist(merchantBO.getAccount())) {
            if (merchantService.queryMerchantForLogin(merchantBO.getAccount(),merchantBO.getPwd())){
                Merchant merchant = merchantService.queryMerchantByAccount(merchantBO.getAccount());
                //将id存入session
                request.getSession().setAttribute("id",merchant.getMerchantId());
                JSONObject result = new JSONObject();
                result.put("status", "success");
                result.put("detail","登录成功！");
                return result.toJSONString();
            }else{
                JSONObject result = new JSONObject();
                result.put("status", "failure");
                result.put("detail","密码错误，登录失败！");
                return result.toJSONString();
            }
        }else{
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","账号不存在，请先注册账号！");
            return result.toJSONString();
        }
    }

    //商家修改账号密码
    @RequestMapping(value = "updatePwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String update(@RequestBody MerchantBO merchantBO,
                         HttpServletRequest request){
        //1.先判断用户的oldPassword是否正确
        int id = Integer.parseInt(request.getSession().getAttribute("id").toString());
        if (!merchantService.updateMerchantPassword(id,merchantBO.getOldPwd(),merchantBO.getNewPwd())){ //不正确
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","用户输入的旧密码错误，修改失败！");
            return result.toJSONString();
        }
        //2.再判断两次输入的密码是否一致
        else if(StringUtils.equals(merchantBO.getOldPwd(),merchantBO.getNewPwd())){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","与原密码一致，修改失败！");
            return result.toJSONString();
        }else {
            JSONObject result = new JSONObject();
            result.put("status", "success");
            result.put("detail","修改密码成功！");
            return result.toJSONString();
        }
    }

    //商家修改brandName
    @RequestMapping(value = "updateBrandName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateBrandName(@RequestBody MerchantBO merchantBO,
                                  HttpServletRequest request){
        int id = Integer.parseInt(request.getSession().getAttribute("id").toString());
        Merchant merchant = merchantService.queryMerchantById(id);
        if(merchantBO.getBrandName().equals(merchant.getBrandName())){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","与brandName一致，修改失败！");
            return result.toJSONString();
        }else {
            merchant.setBrandName(merchantBO.getBrandName());
            merchantService.updateMerchantById(merchant);
            JSONObject result = new JSONObject();
            result.put("status", "success");
            result.put("detail","修改brandName成功！");
            return result.toJSONString();
        }
    }

    //商家退出登录
    @RequestMapping(value = "logout", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void logout(HttpServletRequest request){
        //清空session
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        System.out.println("商家已退出登录！");
    }

    //商家注销账号
    @RequestMapping(value = "logoff", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
        System.out.println("注销成功！");
    }
}
