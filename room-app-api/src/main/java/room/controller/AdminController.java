package room.controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import room.pojo.Merchant;
import room.pojo.Pension;
import room.pojo.Source;
import room.pojo.bo.MerchantBO;
import room.pojo.bo.PensionBO;
import room.pojo.bo.SourceBO;
import room.service.MerchantService;
import room.service.PensionService;
import room.service.SourceService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PensionService pensionService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private SourceService sourceService;

    //商家通过账号密码登录
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestBody MerchantBO merchantBO,
                        HttpSession session) {
        String account = merchantBO.getAccount();
        String pwd = merchantBO.getPwd();
        JSONObject result = new JSONObject();
        if (merchantService.isAccountExist(merchantBO.getAccount())) {
            if (merchantService.queryMerchantForLogin(account, pwd)) {
                int id = merchantService.queryIdByAccount(account);
                session.setAttribute("id", id);  //拦截器需要
                result.put("status", "success");
                result.put("detail", "登录成功！");
            } else {
                result.put("status", "failure");
                result.put("detail", "密码错误，登录失败！");
            }
        } else {
            result.put("status", "failure");
            result.put("detail", "该管理员账号不存在，登录失败！");
        }
        return result.toJSONString();
    }

    //获取商家列表
    @RequestMapping(value = "getMerchantList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getMerchantList() {
        List<Merchant> merchantList = merchantService.queryAll();
        JSONObject result = new JSONObject();

        if (merchantList.isEmpty()) {
            result.put("status", "failure");
            result.put("detail", "我们系统中还没有商家信息哦！");
        } else {
            List<MerchantBO> merchantBOList = new ArrayList<>();
            for (Merchant merchant : merchantList) {
                if (StringUtils.equals(merchant.getBrandName(), "管理员用户")) {
                    continue;   //跳过该次循环，进入下一循环
                } else {
                    MerchantBO merchantBO = new MerchantBO();
                    merchantBO.setId(merchant.getMerchantId());
                    merchantBO.setBrandName(merchant.getBrandName());
                    merchantBO.setAccount(merchant.getAccount());
                    merchantBO.setCreateTime(merchant.getCreateTime());
                    merchantBO.setUpdateTime(merchant.getUpdateTime());
                    merchantBO.setStatus(merchant.getMerchantStatus());
                    merchantBOList.add(merchantBO);
                }
            }
            result.put("status", "success");
            result.put("detail", merchantBOList);
        }
        return result.toJSONString();
    }

    //修改商家状态
    @RequestMapping(value = "updateMerchantStatus", method = RequestMethod.POST, produces = "application/json;" +
            "charset=UTF-8")
    public String updateMerchantStatus(@RequestBody MerchantBO merchantBO) {
        int merchantId = merchantBO.getMerchantId();
        int status = merchantBO.getStatus();
        JSONObject result = new JSONObject();

        if (merchantService.queryMerchantById(merchantId).getMerchantStatus() == 2) {
            result.put("status", "failure");
            result.put("detail", "该商家已注销账户，管理员无法修改其状态！");
        } else {
            merchantService.updateMerchantStatus(merchantId, status);
            result.put("status", "success");
            result.put("detail", "修改状态成功！");
        }


        return result.toJSONString();
    }

    //获取民宿列表
    @RequestMapping(value = "getPensionList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getPensionList() {
        List<Pension> pensionList = pensionService.queryAll();

        JSONObject result = new JSONObject();
        if (pensionList.isEmpty()) {
            result.put("status", "failure");
            result.put("detail", "还没有民宿信息！");
        } else {
            List<PensionBO> pensionBOList = new ArrayList<>();
            for (Pension pension : pensionList) {
                PensionBO pensionBO = new PensionBO();
                pensionBO.setPensionId(pension.getPensionId());
                pensionBO.setAccount(merchantService.queryMerchantById(pension.getMerchantId()).getAccount());
                pensionBO.setBrandName(merchantService.queryMerchantById(pension.getMerchantId()).getBrandName());
                pensionBO.setPensionName(pension.getName());
                pensionBO.setStatus(pension.getPensionStatus());
                if (pension.getImage() == null || "".equals(pension.getImage())){
                    pensionBO.setPensionImg("null");
                }else {
                    pensionBO.setPensionImg(pension.getImage());
                }
                if (pension.getAddressDetail() == null || "".equals(pension.getAddressDetail())){
                    pensionBO.setAddressDetail("null");
                }else {
                    pensionBO.setAddressDetail(pension.getAddressDetail());
                }
                pensionBO.setAddressProvince(pension.getAddressProvince());
                pensionBO.setAddressCity(pension.getAddressCity());
                pensionBO.setAddressDistrict(pension.getAddressDistrict());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                pensionBO.setCreateTime(sdf.format(pension.getCreateTime()));
                pensionBO.setUpdateTime(sdf.format(pension.getUpdateTime()));
                pensionBOList.add(pensionBO);
            }
            result.put("status", "success");
            result.put("detail", pensionBOList);
        }

        return result.toJSONString();
    }

    //修改民宿状态
    @RequestMapping(value = "updatePensionStatus", method = RequestMethod.POST, produces = "application/json;" +
            "charset=UTF-8")
    public String updatePensionStatus(@RequestBody PensionBO pensionBO) {
        int pensionId = pensionBO.getPensionId();
        int status = pensionBO.getStatus();
        JSONObject result = new JSONObject();

        if (pensionService.queryByPensionId(pensionId).getPensionStatus() == 2) {
            result.put("status", "failure");
            result.put("detail", "该民宿所属商家已注销账户，管理员无法修改民宿状态！");
        } else {
            pensionService.updatePensionStatus(pensionId, status);
            result.put("status", "success");
            result.put("detail", "修改状态成功！");
        }
        return result.toJSONString();

    }

    //新建客源
    @RequestMapping(value = "createSource", method = RequestMethod.POST, produces = "application/json;" +
            "charset=UTF-8")
    public String createSource(@RequestBody SourceBO sourceBO) {
        String sourceName = sourceBO.getSourceName();
        JSONObject result = new JSONObject();

        if (sourceService.isSourceExist(sourceName)){
            result.put("status", "failure");
            result.put("detail", "该客源已存在！");
        }else {
            Source source = new Source();
            source.setSourceName(sourceName);
            sourceService.createSource(source);
            result.put("status", "success");
            result.put("sourceId",sourceService.querySourceIdBySourceName(sourceName));
            result.put("detail", "新建客源成功！");
        }
        return result.toJSONString();
    }

    //修改客源名字
    @RequestMapping(value = "updateSourceName", method = RequestMethod.POST, produces = "application/json;" +
            "charset=UTF-8")
    public String updateSourceName(@RequestBody SourceBO sourceBO) {
        int sourceId = sourceBO.getSourceId();
        String sourceName = sourceBO.getSourceName();
        JSONObject result = new JSONObject();

        Source source = new Source();
        source.setSourceId(sourceId);
        source.setSourceName(sourceName);
        sourceService.updateNameBySourceId(source);
        result.put("status", "success");
        result.put("detail", "修改客源名字成功！");

        return result.toJSONString();
    }

    //删除客源
    @RequestMapping(value = "deleteSource", method = RequestMethod.POST, produces = "application/json;" +
            "charset=UTF-8")
    public String deleteSource(@RequestBody SourceBO sourceBO) {
        int sourceId = sourceBO.getSourceId();
        JSONObject result = new JSONObject();

        sourceService.deleteSource(sourceId);
        result.put("status", "success");
        result.put("detail", "删除客源成功！");
        return result.toJSONString();
    }

}
