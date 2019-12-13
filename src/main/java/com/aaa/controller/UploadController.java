package com.aaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("upload")
public class UploadController {

    @RequestMapping("upload")
    public String upload(@RequestParam("photo") MultipartFile photo){
        System.out.println("photo:"+photo);
        // 获取文件原名
        String originalFilename = photo.getOriginalFilename();
        System.out.println(originalFilename);

        boolean empty = photo.isEmpty();// 判断文件是否为空，文件大小为0
        System.out.println("empty:"+empty);

        // 判断是否有上传文件
        if(!"".equals(originalFilename)){
            UUID uuid = UUID.randomUUID();
            String savePath = "F:/"+uuid+"_"+originalFilename;

            File saveFile = new File(savePath);
            // 另存为
            try {
                photo.transferTo(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "index";
    }


    @RequestMapping("upload2")
    public String upload2(MultipartFile photo,MultipartFile photo2){
        System.out.println(photo.getOriginalFilename());
        System.out.println(photo2.getOriginalFilename());
        return "index";
    }

    // 1.多个文件的提交的name值一样；2.必须加@RequestParam
    @RequestMapping("upload3")
    public String upload3(@RequestParam("photo") MultipartFile[] photo){
        System.out.println(photo[0].getOriginalFilename());
        System.out.println(photo[1].getOriginalFilename());
        return "index";
    }
}
