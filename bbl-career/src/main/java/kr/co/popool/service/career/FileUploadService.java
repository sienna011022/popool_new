package kr.co.popool.service.career;

import kr.co.popool.domain.dto.career.FileDto;
import kr.co.popool.repository.career.AmazonS3ResourceStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadService {
  private final AmazonS3ResourceStorage amazonS3ResourceStorage;

  public FileDto save(MultipartFile multipartFile) {
    FileDto fileDto = FileDto.multipartOf(multipartFile);
    amazonS3ResourceStorage.store(fileDto.getPath(), multipartFile);
    return fileDto;
  }
}
