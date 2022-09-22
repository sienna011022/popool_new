package kr.co.popool.service.career;

import com.amazonaws.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import kr.co.popool.domain.dto.career.FileDto;
import kr.co.popool.repository.AmazonS3ResourceStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadService {
  private final AmazonS3ResourceStorage amazonS3ResourceStorage;

  public FileDto save(MultipartFile multipartFile) {
    FileDto fileDto = FileDto.multipartOf(multipartFile);
    amazonS3ResourceStorage.store(fileDto.getPath(), multipartFile);

    return fileDto;
  }

  public ResponseEntity<byte[]> getFile(String fileName) {
    log.info("fileName: " + fileName);
    ResponseEntity<byte[]> result = null;
    try {
      HttpHeaders header = new HttpHeaders();

      // read from S3
      URL url = new URL(getFileURL(fileName));
      HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
      InputStream fileIS = urlConn.getInputStream();

      // MIME regardless of extention
      header.add("Content-Type", "image/png");

      result = new ResponseEntity<>(IOUtils.toByteArray(fileIS), header, HttpStatus.OK);

    } catch (IOException e) {
      log.info("wrong file path");
    }
    return result;
  }



    public String getFileURL(String fileName) {
    System.out.println("넘어오는 파일명 : " + fileName);

    // set expiration
    Date expiration = new Date();
    long expTimeMillis = expiration.getTime();
    expTimeMillis += 1000 * 60 * 60; // 1 hour
    expiration.setTime(expTimeMillis);

    return amazonS3ResourceStorage.get(fileName, expiration);

  }
}
