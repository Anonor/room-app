package room.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import room.resource.FileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private FileUpload fileUpload;

    //上传图片接口
    @RequestMapping(value = "uploadImage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String uploadImage(HttpServletRequest request, MultipartFile file) {
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
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("image", finalUserFaceUrl);
        return result.toJSONString();
    }

}
