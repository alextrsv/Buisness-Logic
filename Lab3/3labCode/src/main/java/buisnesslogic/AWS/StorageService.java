package buisnesslogic.AWS;

import buisnesslogic.Repositories.UserRepository;
import buisnesslogic.model.entity.User;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private UserRepository userRepository;


    public String uploadAvatar(File avatar, String userName){
        User user = userRepository.findByUsername(userName);
        user.setAvatarUrl(uploadFileFromHttp(user, avatar).toString());

        userRepository.save(user);

        return user.getAvatarUrl();
    }


    /**
     * Загрузка файла, полученного в теле HTTP-запроса в storage. На вход получает файл из тела запроса,
     * возвращает временную ссылку на файл в storage
     * @param file - файл из тела запроса
     */
    public URL uploadFileFromHttp(User user, File file) {
        String fileName = user.getUsername();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
        file.delete();
        return generatePublicUrl(fileName);
    }



    public File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Error converting multipartFile to file");
        }
        return convertedFile;
    }

    public URL generatePublicUrl(String objectKey){
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60 * 24 * 7;
        expiration.setTime(expTimeMillis);

        System.out.println("Generating pre-signed URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, objectKey)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        return url;
    }

}
