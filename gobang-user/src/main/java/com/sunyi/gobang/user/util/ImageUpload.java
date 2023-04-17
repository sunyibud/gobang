package com.sunyi.gobang.user.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 图片上传
 */
public class ImageUpload
{
    private static final String foldPath = "/root/user_dist";
    private static final String headPath = "/images/headIcons/";
    /**
     *
     * @param file 二进制文件
     * @return 文件上传结果,成功返回图片路径
     */
    public static String upload(MultipartFile file)
    {
        if (file==null)
            return "文件上传为空或接收异常";
        try {
            long bytes = file.getSize();
            long megabytes = bytes / 1024 / 1024;
            if(megabytes>10)
                return "图片大小不能超过10M";
            File folder = new File(foldPath+headPath);
            if(!folder.exists())
            {
                if(!folder.mkdirs())
                    return "服务器异常，头像文件夹创建失败";
            }
            String oldName = file.getOriginalFilename();
            if(StringUtils.isBlank(oldName))
                return "上传文件格式存在问题";
            String fileType =  oldName.split("\\.")[1];
            if(!fileType.contains("jpg") && !fileType.contains("png")
                    &&!fileType.contains("jpeg"))
                return "图片格式不支持，仅支持jpg/jpeg/png";

            String newName = UUID.randomUUID().toString()+
                    oldName.substring(oldName.lastIndexOf("."));
            file.transferTo(new File(folder, newName));

            return headPath+newName;
        } catch (Exception e) {
            return "出现错误，上传失败";
        }

    }
}
