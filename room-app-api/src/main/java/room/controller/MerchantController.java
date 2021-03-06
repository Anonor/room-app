package room.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import room.common.utils.MySessionContext;
import room.common.utils.SendEmail;
import room.pojo.Merchant;
import room.pojo.bo.MerchantBO;
import room.service.MerchantService;
import room.service.OrderService;
import room.service.PensionService;
import room.service.RoomService;

import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PensionService pensionService;
    @Autowired
    private RoomService roomService;

    //获取邮箱验证码
    @RequestMapping(value = "getEmailVerificationCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getEmailVerificationCode(@RequestBody MerchantBO merchantBO,
                                           HttpSession session){
        //获取邮箱验证码事务类型（注册、通过邮箱验证码登录、重置密码）
        String type = merchantBO.getType();
        JSONObject result = new JSONObject();
        //根据type进行对账号有效性进行判断
        switch (type){
            case "注册":
                //注册的账号已存在
                if(merchantService.isAccountExist(merchantBO.getAccount())) {
                    result.put("status","failure");
                    result.put("detail","账号已存在，请直接返回首页登录!");
                    //清空session所有数据
                    MySessionContext.delSession(session);
                    return result.toJSONString();
                }else { //账号不存在
                    //注册必须通过邮箱验证码验证，故将注册页面添加进了拦截页面防止恶意注册，只有通过邮箱验证码才可注册账号
                    //设置session中id不为空，以免即便通过邮箱验证了的用户注册页面还被拦截下来，此处在web中使用（web有拦截器了)
                    //session.setAttribute("id",1);//注册用户没有id，只需设不为空即可
                    //保存注册账号，register函数会使用
                    session.setAttribute("account",merchantBO.getAccount());
                }
                break;
            case "通过邮箱验证码登录":case "重置密码":
                if (!merchantService.isAccountExist(merchantBO.getAccount())){
                    result.put("status", "failure");
                    result.put("detail","账号不存在，请先注册账号！");
                    //清空session所有数据
                    MySessionContext.delSession(session);
                    return result.toJSONString();
                }else {     //账号存在
                    //存id
                    int id = merchantService.queryIdByAccount(merchantBO.getAccount());
                    session.setAttribute("id",id);
                    session.setAttribute("account",merchantBO.getAccount());
                }
                break;
            default:
                break;
        }
        //发送验证码邮件
        String code = String.valueOf(new Random().nextInt(999999));//随机生成验证码
        SendEmail sendEmail = new SendEmail();
        sendEmail.SendSimpleEmail("【LY民宿管理系统】"+type+"验证码","【LY民宿管理系统】您的验证码为："+code+"，您正在进行"+type+"操作，该验证码有效期为2分钟请及时输入，如非本人操作，请忽略本邮件！",merchantBO.getAccount());
        session.setAttribute("code",code);
        System.out.println("code:"+code);
        session.setAttribute("createTime",System.currentTimeMillis());
        result.put("status", "success");
        result.put("detail","验证码已发送请注意查收，开始进行"+type+"操作的邮箱验证！");
        //存session
        MySessionContext.addSession(session);
        result.put("sessionId",session.getId());
        return result.toJSONString();
    }


    //商家注册账号（需进行邮箱验证）
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  register(@RequestBody MerchantBO merchantBO){
        //用户输入的验证码
        String codeMerchant = merchantBO.getCode();
        JSONObject result = new JSONObject();
        HttpSession session = MySessionContext.getSession(merchantBO.getSessionId());
        Long codeTime = Long.parseLong(session.getAttribute("createTime").toString());
        //验证用户输入的验证码是否正确
        if (validateCode(session.getAttribute("code").toString(),session.getAttribute("createTime").toString(),codeMerchant)){
            Merchant merchant = new Merchant();
            String account = session.getAttribute("account").toString();
            merchant.setAccount(account);
            merchant.setBrandName(merchantBO.getBrandName());
            merchant.setPassword(merchantBO.getPwd());
            merchantService.createMerchant(merchant);
            result.put("status", "success");
            result.put("detail","账号注册成功！");
            //注册成功，清空session
            MySessionContext.delSession(session);
            return result.toJSONString();
        }else if (System.currentTimeMillis()-codeTime>120000) {     //验证码输入错误且超时
            //验证码超时，清空session，验证码作废
            MySessionContext.delSession(session);
        }

        result.put("status", "failure");
        result.put("detail","验证码错误或超过了2分钟才输入验证码,请在两分钟内重新输入验证码或重新获取验证码！");
        return result.toJSONString();
    }


    //商家通过账号密码登录
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestBody MerchantBO merchantBO,
                        HttpSession session){
        //先判断该用户之前是否有sessionId，先删掉原来的session
        if (MySessionContext.getSession(session.getId())!=null){       //说明该用户之前已有session
            System.out.println("您已有session!且sessionId:"+session.getId());
            MySessionContext.delSessionById(session.getId());
        }
        //该用户之前没有session或已被删除了
        if(merchantService.isAccountExist(merchantBO.getAccount())) {
            if (merchantService.queryMerchantForLogin(merchantBO.getAccount(),merchantBO.getPwd())){
                Merchant merchant = merchantService.queryMerchantByAccount(merchantBO.getAccount());
                //将id存入session
                session.setAttribute("id",merchant.getMerchantId());
                //存session
                MySessionContext.addSession(session);
                JSONObject result = new JSONObject();
                result.put("status", "success");
                result.put("detail","登录成功！");
                result.put("sessionId",session.getId());
                return result.toJSONString();
            }else{
                JSONObject result = new JSONObject();
                result.put("status", "failure");
                result.put("detail","密码错误，登录失败！");
                MySessionContext.delSession(session);
                return result.toJSONString();
            }
        }else{
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","账号不存在，请先注册账号！");
            MySessionContext.delSession(session);
            return result.toJSONString();
        }
    }

    //用户通过邮箱验证码登录
    @RequestMapping(value = "loginViaEmailCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String loginViaEmailCode(@RequestBody MerchantBO merchantBO){
        //用户输入的验证码
        String codeMerchant = merchantBO.getCode();
        HttpSession session = MySessionContext.getSession(merchantBO.getSessionId());
        JSONObject result = new JSONObject();
        Long codeTime = Long.parseLong(session.getAttribute("createTime").toString());
        //验证用户输入的验证码是否正确
        if (validateCode(session.getAttribute("code").toString(),session.getAttribute("createTime").toString(),codeMerchant)){
            result.put("status", "success");
            result.put("detail","验证码正确，登录成功！");
            return result.toJSONString();
        }else if (System.currentTimeMillis()-codeTime>120000) {     //验证码输入错误且超时
            //验证码超时，清空session，验证码作废
            MySessionContext.delSession(session);
        }
        result.put("status", "failure");
        result.put("detail","验证码错误或超过了2分钟才输入验证码,请在两分钟内重新输入验证码或重新获取验证码！");
        return result.toJSONString();
    }

    //商家修改账号密码（登录情况下）
    @RequestMapping(value = "updatePwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String update(@RequestBody MerchantBO merchantBO){
        //1.先判断用户的oldPassword是否正确
        HttpSession session = MySessionContext.getSession(merchantBO.getSessionId());
        int id = Integer.parseInt(session.getAttribute("id").toString());
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

    //商家忘记密码（非登录及登录情况下），修改密码成功之后必须重新登录
    @RequestMapping(value = "forgetPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  forgetPwd(@RequestBody MerchantBO merchantBO){
        //用户输入的验证码
        String codeMerchant = merchantBO.getCode();
        HttpSession session = MySessionContext.getSession(merchantBO.getSessionId());
        JSONObject result = new JSONObject();
        Long codeTime = Long.parseLong(session.getAttribute("createTime").toString());
        //验证用户输入的验证码是否正确
        if (validateCode(session.getAttribute("code").toString(),session.getAttribute("createTime").toString(),codeMerchant)){
            String pwd = merchantBO.getPwd();
            String account = session.getAttribute("account").toString();
            int id = merchantService.queryIdByAccount(account);
            Merchant merchant = merchantService.queryMerchantById(id);
            merchant.setPassword(pwd);
            merchantService.updateMerchantById(merchant);
            //清空session
            MySessionContext.delSession(session);
            result.put("status", "success");
            result.put("detail","修改密码成功！");
            return result.toJSONString();
        }else if (System.currentTimeMillis()-codeTime>120000) {     //验证码输入错误且超时
            //验证码超时，清空session，验证码作废
            MySessionContext.delSession(session);
        }
        result.put("status", "failure");
        result.put("detail","验证码错误或超过了2分钟才输入验证码,请在两分钟内重新输入验证码或重新获取验证码！");
        return result.toJSONString();

        //进行短信验证（充值榛子云即可用hhhh，现余额不足）
        /*CodeController codeController = new CodeController();
        codeController.getCode(merchantBO.getAccount(),httpSession);
        httpSession.setAttribute("account",merchantBO.getAccount());
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail","账号存在，开始进行短信验证！");
        return result.toJSONString();*/
    }

    //判断验证码是否正确和验证码是否已过期
    public Boolean validateCode(String codeCorrect, String createTime, String codeMerchant){
        Long codeTime = Long.parseLong(createTime);
        //发送的邮箱验证码与用户输入的邮箱验证码的时间间隔（ms）
        Long interval = System.currentTimeMillis()-codeTime;
        //验证码正确，且在两分钟之内输入验证码，2分(min)=120000毫秒(ms)
        if(StringUtils.equals(codeCorrect,codeMerchant) && interval<=120000){
            return true;
        }
        return false;
    }

    //商家修改brandName，必须在登录状态下
    @RequestMapping(value = "updateBrandName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateBrandName(@RequestBody MerchantBO merchantBO){
        HttpSession session = MySessionContext.getSession(merchantBO.getSessionId());
        int id = Integer.parseInt(session.getAttribute("id").toString());
        Merchant merchant = merchantService.queryMerchantById(id);
        if(merchantBO.getBrandName().equals(merchant.getBrandName())){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","与原brandName一致，修改失败！");
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
    public void logout(@RequestBody MerchantBO merchantBO){
        //清空session
        MySessionContext.delSessionById(merchantBO.getSessionId());
        System.out.println("商家已退出登录！");
    }

    //商家注销账号（所有关联表置状态为2:关闭）
    @RequestMapping(value = "logoff", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void logoff(@RequestBody MerchantBO merchantBO){
        HttpSession session=MySessionContext.getSession(merchantBO.getSessionId());
        //置merchant表中该商家账号状态为2
        int id = Integer.parseInt(session.getAttribute("id").toString());
        merchantService.updateMerchantStatus(id,2);
        //置order表中该商家所属订单状态为2（订单无效）
        orderService.deleteByMerchantId(id);
        //置pension表中该商家所属门店状态为2
        pensionService.deletePensionByMerchantId(id,2);
        //置room表中该商家所属房间状态为2
        roomService.updateRoomStatusByMerchantId(id,2);
        //清空session
        MySessionContext.delSession(session);
        System.out.println("商家注销成功！");
    }

    //根据sessionId得到商家昵称（brandName）及密码
    @RequestMapping(value = "getBrandNameAndPwdBySI", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getBrandNameAndPwdBySI(@RequestBody MerchantBO merchantBO){
        String sessionId = merchantBO.getSessionId();
        int id = Integer.parseInt(MySessionContext.getSession(sessionId).getAttribute("id").toString());
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail","获取账号成功！");
        result.put("brandName",merchantService.queryMerchantById(id).getBrandName());
        result.put("pwd",merchantService.queryMerchantById(id).getPassword());
        return result.toJSONString();
    }
}
