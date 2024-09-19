package kr.codeback.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

@Service
public class S3Service {
    private final String bucket;
    private final AmazonS3 amazonS3;

    public S3Service(AmazonS3 amazonS3, @Value("${cloud.aws.s3.bucket}") String bucket) {
        this.bucket = bucket;
        this.amazonS3 = amazonS3;
    }

    public String upload(MultipartFile multipartFile, String s3Filename) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getInputStream().available());
        amazonS3.putObject(bucket, s3Filename, multipartFile.getInputStream(), objectMetadata);

        return URLDecoder.decode(amazonS3.getUrl(bucket, s3Filename).toString(), "utf-8");
    }

    public void delete (String fileName) throws AmazonServiceException{
        amazonS3.deleteObject(bucket, fileName);
    }

    public void deleteS3Files(List<String> fileNames) throws AmazonServiceException {

        // 삭제 요청 생성
        DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucket)
            .withKeys(fileNames.toArray(new String[0]));

        // 객체 삭제
        amazonS3.deleteObjects(deleteRequest);
    }
}
