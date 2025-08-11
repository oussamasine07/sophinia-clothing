package com.sophinia.backend.bean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class FileUpload {

    public String upload (MultipartFile file, String dir) {

        // 1️⃣ Ensure the directory path is correct
        String uploadDir = System.getProperty("user.dir")
                + File.separator + "backend"
                + File.separator + "uploads"
                + File.separator + dir;

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // creates parent dirs if needed
        }

        // 2️⃣ Generate unique file name with original extension
        String originalName = file.getOriginalFilename();
        String extension = "";
        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }
        String uniqueFileName = System.currentTimeMillis() + extension;

        // 3️⃣ Create file object
        File uploadedFile = new File(directory, uniqueFileName);

        try {
            file.transferTo(uploadedFile);
        } catch (Exception e) {
            throw new RuntimeException("File upload failed", e);
        }

        // 4️⃣ Return relative path
        return "uploads" + File.separator + dir + File.separator + uniqueFileName;
    }

    public void deleteFile (String filePath) {
        String path = System.getProperty("user.dir")
                + File.separator + "backend"
                + File.separator + filePath;

        File file = new File( path );
        if (file.exists()) {
            file.delete();
        }
    }

}






















