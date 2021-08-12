package pl.lepa.crudapp.service;

import com.google.common.io.Files;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class UploadService {

    private static final String CRUDAPP_SRC_MAIN_RESOURCES_IMAGES = "crudapp/src/main/resources/images";

    public String uploadLocalFile(MultipartFile file) {

        File uploadDirection = new File(CRUDAPP_SRC_MAIN_RESOURCES_IMAGES);
        if (!uploadDirection.exists()) {
            uploadDirection.mkdirs();
        }
        uploadDirection.getAbsolutePath();
        String filename = file.getOriginalFilename();
        assert filename != null;

        String uuid = UUID.randomUUID().toString();
        String extension = Files.getFileExtension(filename);
        String filenameWithoutExtension = Files.getNameWithoutExtension(filename);

        File image = new File(CRUDAPP_SRC_MAIN_RESOURCES_IMAGES
                + "/"
                + filenameWithoutExtension
                + uuid
                + "."
                + extension);

        try (OutputStream outputStream = new FileOutputStream(image);
             InputStream inputStream = file.getInputStream();
        ) {
            IOUtils.copy(inputStream, outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return image.getPath();
    }

    public String uploadToCloud(MultipartFile file) {
        return "";
    }


}
