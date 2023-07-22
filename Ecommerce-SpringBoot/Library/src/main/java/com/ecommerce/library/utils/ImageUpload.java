package com.ecommerce.library.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUpload {
    private  final String UPLOAD_FOLDER = "D:\\New folder (2)\\ShopCart\\Ecommerce-SpringBoot\\Admin\\src\\main\\resources\\static\\img\\image-product";
    private  final String UPLOAD_PROFILE = "D:\\New folder (2)\\ShopCart\\Ecommerce-SpringBoot\\Customer\\src\\main\\resources\\static\\img\\profile";

    public boolean uploadFile(MultipartFile file) {
        boolean isUpload = false;
        try {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_FOLDER + File.separator + file.getOriginalFilename()) , StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpload;
    }



    public boolean checkExist(MultipartFile multipartFile){
        boolean isExist = false;
        try {
            File file = new File(UPLOAD_FOLDER +"\\" + multipartFile.getOriginalFilename());
            isExist = file.exists();
        }catch (Exception e){
            e.printStackTrace();
        }
        return isExist;
    }

}
