package kr.co.popool.domain.dto.career;

import java.time.LocalDateTime;
import kr.co.popool.infra.config.MultipartUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FileDto {

  private String id;
  private String name;
  private String format;
  private String path;
  private long bytes;

  @Builder.Default
  private LocalDateTime createdAt = LocalDateTime.now();

  public static FileDto multipartOf(MultipartFile multipartFile) {
    final String fileId = MultipartUtil.createFileId();
    final String format = MultipartUtil.getFormat(multipartFile.getContentType());
    return FileDto.builder()
        .id(fileId)
        .name(multipartFile.getOriginalFilename())
        .format(format)
        .path(MultipartUtil.createPath(fileId, format))
        .bytes(multipartFile.getSize())
        .build();
  }
}