package pl.lepa.crudapp.service;

import com.google.common.io.Files;
import com.sun.istack.NotNull;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class UploadService {

    public String uploadLocalFile(MultipartFile file) {
        File uploadDirection = new File("/src/main/resources/image");
        if (!uploadDirection.exists()) {
            uploadDirection.mkdirs();
        }

        String filename = file.getOriginalFilename();
        assert filename != null;

        String uuid = UUID.randomUUID().toString();
        String extension = Files.getFileExtension(filename);
        String filenameWithoutExtension = Files.getNameWithoutExtension(filename);

        File image = new File("/src/main/resources/image"
                + filenameWithoutExtension
                + uuid
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

    public String uploadOnCloud(MultipartFile file) {
        return "";
    }


}
