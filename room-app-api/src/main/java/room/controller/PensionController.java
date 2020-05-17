package room.controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import room.common.utils.GetLngAndLatUtil;
import room.common.utils.MySessionContext;
import room.pojo.Pension;
import room.pojo.Room;
import room.pojo.bo.PensionBO;
import room.pojo.vo.PensionVO;
import room.pojo.vo.RoomGroupVO;
import room.pojo.vo.RoomVO;
import room.resource.FileUpload;
import room.service.PensionService;
import room.service.RoomService;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/pension")
public class PensionController {

    @Autowired
    private PensionService pensionService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private FileUpload fileUpload;

    //上传图片接口
    @RequestMapping(value = "uploadImage", method = RequestMethod.POST)
    public String uploadImage(MultipartFile file) {
        String fileSpace = fileUpload.getImageUserFaceLocation();

        String uploadPathPrefix = "";

        //开始文件上传
        if (file != null) {
            //获得文件上传的文件名称
            String fileName = file.getOriginalFilename();
            if (StringUtils.isNotBlank(fileName)) {
                String[] fileNameArr = StringUtils.split(fileName, "\\.");

                //获取文件后缀名
                String suffix = fileNameArr[fileNameArr.length - 1];

                if (!suffix.equalsIgnoreCase("png")
                        &&!suffix.equalsIgnoreCase("jpg")
                        &&!suffix.equalsIgnoreCase("jpeg")
                        &&!suffix.equalsIgnoreCase("gif")) {
                    JSONObject result = new JSONObject();
                    result.put("status", "failure");
                    result.put("detail","图片格式不正确");
                    return result.toJSONString();
                }

                //文件名称重组，这是覆盖式上传      增量式：额外拼接当前时间
                String newFileName = UUID.randomUUID().toString() + "." + suffix;

                //上传的头像最终保存的位置
                String finalFacePath = fileSpace + File.separator + newFileName;

                //图片更新的路径(用于提供web服务访问的地址)
                uploadPathPrefix = newFileName;

                File outFile = new File(finalFacePath);
                if (outFile.getParentFile() != null) {
                    //创建文件夹
                    outFile.getParentFile().mkdirs();
                }

                FileOutputStream fileOutputStream = null;
                try {
                    //文件输出保存到目录
                    fileOutputStream = new FileOutputStream(outFile);
                    InputStream inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fileOutputStream != null) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        } else {
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail", "文件不能为空");
            return result.toJSONString();
        }

        //获取图片服务地址
        String imageServerUrl = fileUpload.getImageServerUrl();
        String finalUserFaceUrl = imageServerUrl + uploadPathPrefix
                + "?t=" + System.currentTimeMillis();
        return finalUserFaceUrl;
    }

    //商家增加民宿
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String add(@RequestBody PensionBO pensionBO){
        HttpSession session = MySessionContext.getSession(pensionBO.getSessionId());
        int merchantId = Integer.parseInt(session.getAttribute("id").toString());
        if(pensionService.isPensionExist(merchantId,pensionBO.getPensionName())){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","民宿已存在，增加民宿失败！");
            return result.toJSONString();
        }
        Pension pension = new Pension();
        pension.setMerchantId(merchantId);
        pension.setName(pensionBO.getPensionName());
        pension.setAddressProvince(pensionBO.getAddressProvince());
        pension.setAddressCity(pensionBO.getAddressCity());
        pension.setAddressDistrict(pensionBO.getAddressDistrict());
        pension.setAddressDetail(pensionBO.getAddressDetail());
        //拼接地址得到该地址的经纬度
        String address = pensionBO.getAddressProvince()+pensionBO.getAddressCity()
                +pensionBO.getAddressDistrict()+ pensionBO.getAddressDetail();
        Map<String, Double> map = new HashMap<String, Double>();
        try {
            map = GetLngAndLatUtil.getLngAndLat(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pension.setLongitude(map.get("lng").toString());
        pension.setLatitude(map.get("lat").toString());
        //如果商家上传了门店图片，则存入数据库
        if (pensionBO.getPensionImg()!=null){
            pension.setImage(pensionBO.getPensionImg());
        }
        pensionService.createPension(pension);
        int pensionId = pensionService.getPensionId(Integer.parseInt(session.getAttribute("id").toString()),pensionBO.getPensionName());
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail","添加民宿成功！");
        result.put("pensionId",pensionId);
        return result.toJSONString();
    }


    //显示该商家拥有的所有民宿的信息
    @RequestMapping(value = "showAllPensionInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String showAllPensionInfo(@RequestBody PensionBO pensionBO){
        HttpSession session = MySessionContext.getSession(pensionBO.getSessionId());
        int merchantId = Integer.parseInt(session.getAttribute("id").toString());
        List<Pension> pensions = pensionService.queryByMerchantId(merchantId);

        ArrayList<PensionVO> pensionVOs = new ArrayList();
        for (int i=0; i<pensions.size(); i++){
            //只返回未关闭的民宿（pensionStatus！=2)
            if (pensions.get(i).getPensionStatus()!=2){
                PensionVO pensionVO = new PensionVO();
                pensionVO.setPensionId(pensions.get(i).getPensionId());
                pensionVO.setName(pensions.get(i).getName());
                String address = pensions.get(i).getAddressProvince()+pensions.get(i).getAddressCity()
                        +pensions.get(i).getAddressDistrict()+pensions.get(i).getAddressDetail();
                pensionVO.setAddress(address);
                pensionVO.setPensionStatus(pensions.get(i).getPensionStatus());
                String location=pensions.get(i).getAddressProvince()+pensions.get(i).getAddressCity()
                        +pensions.get(i).getAddressDistrict();
                String detail = pensions.get(i).getAddressDetail();
                pensionVO.setLocation(location);
                pensionVO.setDetail(detail);
                pensionVOs.add(pensionVO);
            }
        }
        JSONObject result = new JSONObject();
        if (pensionVOs.size()==0){
            result.put("status","failure");
            result.put("detail","您还没有民宿！");
            return result.toJSONString();
        }
        result.put("status", "success");
        result.put("detail",pensionVOs);
        return result.toJSONString();
        //return JSONObject.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
    }

    //显示这个民宿的经纬度信息
    @RequestMapping(value = "showPensionLngAndLatById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String showPensionLngAndLatById(@RequestBody PensionBO pensionBO){
        int pensionId = pensionBO.getPensionId();
        Pension pension = pensionService.queryByPensionId(pensionId);
        String longitude = pension.getLongitude();
        String latitude = pension.getLatitude();
        JSONObject result = new JSONObject();
        result.put("longitude",longitude);
        result.put("latitude",latitude);
        return result.toJSONString();
    }

    //修改民宿信息
    @RequestMapping(value = "updatePensionInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatePensionInfo(@RequestBody PensionBO pensionBO){
        //查询该商家修改后的民宿信息是否存在
        HttpSession session = MySessionContext.getSession(pensionBO.getSessionId());
        int merchantId = Integer.parseInt(session.getAttribute("id").toString());
        if (pensionService.isPensionExist(merchantId,pensionBO.getPensionName())){     //存在
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","修改后的民宿信息已存在，修改民宿信息失败！");
            return result.toJSONString();
        }
        int pensionId = pensionBO.getPensionId();
        Pension pension = pensionService.queryByPensionId(pensionId);
        pension.setName(pensionBO.getPensionName());
        pension.setAddressProvince(pensionBO.getAddressProvince());
        pension.setAddressCity(pensionBO.getAddressCity());
        pension.setAddressDistrict(pensionBO.getAddressDistrict());
        pension.setAddressDetail(pensionBO.getAddressDetail());
        //拼接地址得到该地址的经纬度
        String address = pensionBO.getAddressProvince()+pensionBO.getAddressCity()
                +pensionBO.getAddressDistrict()+ pensionBO.getAddressDetail();
        Map<String, Double> map = new HashMap<String, Double>();
        try {
            map = GetLngAndLatUtil.getLngAndLat(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pension.setLongitude(map.get("lng").toString());
        pension.setLatitude(map.get("lat").toString());
        pensionService.updatePension(pension);
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail","修改民宿信息成功！");
        return result.toJSONString();
    }

    //删除这个民宿的信息
    @RequestMapping(value = "deletePensionById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deletePensionById(@RequestBody PensionBO pensionBO){
        int pensionId = pensionBO.getPensionId();
        //置该民宿状态为2，即关闭状态
        pensionService.updatePensionStatus(pensionId,2);
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail","删除民宿信息成功！");
        return result.toJSONString();
    }

    //根据民宿Id获得分组列表
    @RequestMapping(value = "getGroupsByPensionId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getGroupsByPensionId(@RequestBody PensionBO pensionBO){
        int pensionId = pensionBO.getPensionId();
        List<RoomGroupVO> roomGroupVOS=new ArrayList<>();
        for (RoomGroupVO roomGroupVO : roomService.queryValidRoomGroupsByPensionId(pensionId)){
            RoomGroupVO groupVO=new RoomGroupVO();
            groupVO.setGroupName(roomGroupVO.getGroupName());
            groupVO.setGroupId(roomGroupVO.getGroupId());
            roomGroupVOS.add(groupVO);
        }

        if (roomGroupVOS.isEmpty()){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","您还没有分组信息！");
            return result.toJSONString();
        }
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail",roomGroupVOS);
        return result.toJSONString();
    }

    //根据组Id获得房间列表
    @RequestMapping(value = "getRoomsByGroupId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getRoomsByGroupId(@RequestBody PensionBO pensionBO){
        int groupId = pensionBO.getGroupId();
        List<RoomVO> roomVOS=new ArrayList<>();
        for (Room room : roomService.queryRoomsByGroupIdAndRoomStatus(groupId, 0)){
            RoomVO roomVO=new RoomVO();
            roomVO.setRoomName(room.getName());
            roomVO.setRoomId(room.getRoomId());
            roomVO.setRoomStatus(room.getRoomStatus());
            roomVOS.add(roomVO);
        }
        if (roomVOS.isEmpty()){
            JSONObject result = new JSONObject();
            result.put("status", "failure");
            result.put("detail","该分组下还没有房间！");
            return result.toJSONString();
        }
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("detail",roomVOS);
        return result.toJSONString();
    }

}
