package com.sophinia.backend.bean;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileUpload {

    private final Cloudinary cloudinary;

    public FileUpload (
            final Cloudinary cloudinary
    ) {
        this.cloudinary = cloudinary;
    }

    public String upload (MultipartFile file, String dir) {

        try{
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", dir);
            Map<Object, Object> uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            String publicId = (String) uploadedFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void deleteFile(String fileUrl) {
        String publicId = extractPublicId( fileUrl );
        try {
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            System.out.println("Delete result: " + result);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file from Cloudinary: " + publicId, e);
        }
    }

    private String extractPublicId(String url) {
        String withoutBase = url.substring(url.indexOf("/upload/") + 8);
        // remove version if present
        withoutBase = withoutBase.replaceFirst("^v\\d+/", "");
        // remove file extension
        int dotIndex = withoutBase.lastIndexOf(".");
        if (dotIndex != -1) {
            withoutBase = withoutBase.substring(0, dotIndex);
        }
        return withoutBase;
    }

}






















