package uz.online.blog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
@ToString
public class AppErrorDTO {
    private String errorMessage;
    private Integer errorCode;
    private String errorPath;
    private LocalDateTime timestamp;

    public AppErrorDTO(String errorMessage, Integer errorCode, String errorPath, LocalDateTime timestamp) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorPath = errorPath;
        this.timestamp = timestamp;
    }
}
