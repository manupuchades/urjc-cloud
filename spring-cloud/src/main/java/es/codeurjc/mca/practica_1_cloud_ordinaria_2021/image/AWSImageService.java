package es.codeurjc.mca.practica_1_cloud_ordinaria_2021.image;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service("storageService")
@Profile("production")
public class AWSImageService implements ImageService {

    @Value("${amazon.s3.region}")
    private String region;

    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    @Value("${amazon.s3.endpoint}")
    private String endpoint;

    public static AmazonS3 s3;

    @PostConstruct
    public void init() {
        s3 = AmazonS3ClientBuilder.standard().withRegion(region).build();

        createBucket(bucketName);
    }

    @Override
    public String createImage(MultipartFile multiPartFile) {
        String fileName = "image_" + UUID.randomUUID() + "_" + multiPartFile.getOriginalFilename();
        File file = new File(System.getProperty("java.io.tmpdir") + "/events/" + fileName);

        try {
            multiPartFile.transferTo(file);
            PutObjectRequest por = new PutObjectRequest(bucketName, fileName, file);
            por.setCannedAcl(CannedAccessControlList.PublicRead);
            s3.putObject(por);
        } catch (IllegalStateException | IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't save image on aws server", e);
        }
        return s3.getUrl(bucketName, "events/" + fileName).toString();
    }

    @Override
    public void deleteImage(String image) {
        try {
            s3.deleteObject(bucketName, image.replace(endpoint, ""));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Can't delete image from aws server");
        }
    }

    private void createBucket(String bucketName) {
        if (!s3.doesBucketExistV2(bucketName)) {
            s3.createBucket(bucketName);
        }
    }

}
