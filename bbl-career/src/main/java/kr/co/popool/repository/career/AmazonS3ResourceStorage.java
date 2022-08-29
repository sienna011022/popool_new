package kr.co.popool.repository.career;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import com.amazonaws.services.s3.AmazonS3Client;
import kr.co.popool.infra.config.MultipartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class AmazonS3ResourceStorage {

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;
  private final AmazonS3Client amazonS3Client;

  public void store(String fullPath, MultipartFile multipartFile) {
    File file = new File(MultipartUtil.getLocalHomeDirectory(), fullPath);
    try {
      multipartFile.transferTo(file);
      amazonS3Client.putObject(new PutObjectRequest(bucket, fullPath, file)
          .withCannedAcl(CannedAccessControlList.PublicRead));
    } catch (Exception e) {
      throw new RuntimeException();
    } finally {
      if (file.exists()) {
        file.delete();
      }
    }
  }
}